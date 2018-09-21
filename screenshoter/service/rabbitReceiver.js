const bunnyBus = require('./rabbitConf.js');

var rabbitReceiver = function () {
  
  (async () => {
		await bunnyBus.subscribe('data-queue', { 
			'create-event' : (message, ack) => {
				console.log(message);
				ack();
		}})
	})();

}

module.exports = rabbitReceiver;