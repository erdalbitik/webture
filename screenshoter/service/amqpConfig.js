var amqp = require('amqplib/callback_api');
const screenCapturer = require('./capturer.js');
var amqpConn = null;

var amqpStart = function() {
  amqp.connect("amqp://guest:guest@localhost:5672/" + "?heartbeat=60", function(err, conn) {
    if (err) {
      console.error("[AMQP]", err.message);
      return setTimeout(amqpStart, 1000);
    }
    conn.on("error", function(err) {
      if (err.message !== "Connection closing") {
        console.error("[AMQP] conn error", err.message);
      }
    });
    conn.on("close", function() {
      console.error("[AMQP] reconnecting");
      return setTimeout(amqpStart, 1000);
    });
    console.log("[AMQP] connected");
    amqpConn = conn;
    whenConnected();
  });
}

function whenConnected() {
  startPublisher();
  startWorker();
}

var pubChannel = null;
var offlinePubQueue = [];
function startPublisher() {
  amqpConn.createConfirmChannel(function(err, ch) {
    if (closeOnErr(err)) return;
      ch.on("error", function(err) {
      console.error("[AMQP] channel error", err.message);
    });
    ch.on("close", function() {
      console.log("[AMQP] channel closed");
    });

    pubChannel = ch;
    while (true) {
      var m = offlinePubQueue.shift();
      if (!m) break;
      publish(m[0], m[1], m[2]);
    }
  });
}

function publish(exchange, routingKey, content) {
  try {
    console.log("message is publishing!");
  	pubChannel.sendToQueue('turn-queue', content, {persistent: true}, function(err, ok) {
                        if (err) {
                          console.error("[AMQP] publish", err);
                          offlinePubQueue.push([exchange, routingKey, content]);
                          pubChannel.connection.close();
                        }
                      });
  
    /*pubChannel.publish(exchange, routingKey, content, { persistent: true },
                      function(err, ok) {
                        if (err) {
                          console.error("[AMQP] publish", err);
                          offlinePubQueue.push([exchange, routingKey, content]);
                          pubChannel.connection.close();
                        }
                      });*/
  } catch (e) {
    console.error("[AMQP] publish", e.message);
    offlinePubQueue.push([exchange, routingKey, content]);
  }
}

function startWorker() {
  amqpConn.createChannel(function(err, ch) {
    if (closeOnErr(err)) return;
    ch.on("error", function(err) {
      console.error("[AMQP] channel error", err.message);
    });
    ch.on("close", function() {
      console.log("[AMQP] channel closed");
    });

    ch.prefetch(10);
    ch.assertQueue("data-queue", { durable: true }, function(err, _ok) {
      if (closeOnErr(err)) return;
      ch.consume("data-queue", function(msg) {processMsg(msg, ch);}, { noAck: false });
      console.log("Worker is started");
    });
  });
}

function processMsg(msg, ch) {
  work(msg, function(ok) {
    try {
      if (ok)
        ch.ack(msg);
      else
        ch.reject(msg, true);
    } catch (e) {
      closeOnErr(e);
    }
  });
}

function work(msg, cb) {
  console.log("Message received");
  cb(true);
  var message = JSON.parse(msg.content.toString());
  var url = message.url;
  var messageId = message.messageId;
  var name = url.replace(/(^\w+:|^)\/\//, '')+"-"+(new Date()).toISOString();
  
  screenCapturer(url, name, function(driveUrl) {
  		sendMessage = {
  			messageId: messageId,
  			url: url,
  			screenshotUrl: driveUrl
  		};
  		console.log("Publish is about to work!");
  		publish("webture:fileProcess", "turn-queue", Buffer.from(JSON.stringify(sendMessage))); 
  	}
  );
}

function closeOnErr(err) {
  if (!err) return false;
  console.error("[AMQP] error", err);
  amqpConn.close();
  return true;
}

module.exports = amqpStart;
