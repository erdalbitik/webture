const BunnyBus = require('bunnybus');

const  bunnyBus = new BunnyBus({
    protocol : process.env.RABBIT_PROTOCOL,
    user     : "guest",
    password : "guest",
    server   : "rabbitmq",
    port     : "5672"
});

module.exports = bunnyBus;