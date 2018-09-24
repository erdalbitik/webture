var express = require("express");
var bodyParser = require("body-parser");
//const puppeteer = require('puppeteer');
var routes = require("./routes/routes.js");
//var rabbitReceiver = require("./service/rabbitReceiver.js");
var amqpStart = require("./service/amqpConfig.js");
var app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

routes(app);

amqpStart();

var server = app.listen(3001, function () {
    console.log("app running on port.", server.address().port);
});