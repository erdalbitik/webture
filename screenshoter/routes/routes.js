//const puppeteer = require('puppeteer');
//var capturer = require("../service/capturer.js");

var appRouter = function (app) {
  app.post("/", function(req, res) {
	  console.log(req.body.url);
	  console.log(req.body.path);
	  //capturer(puppeteer, req.body.url, req.body.path);
	res.status(200).send("Welcome to our restful API");
  });

}

module.exports = appRouter;
