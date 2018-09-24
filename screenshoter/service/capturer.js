const puppeteer = require('puppeteer');
const path = require('path');
const os = require('os');
const createFile = require('./driveConfig.js');

var screenCapturer = function (url, fileName, callback) {
  console.log("url:"+url);
  var imageName = fileName+".png";
  var tempDir = os.tmpdir();
  const imagePath = path.join(tempDir, imageName);
  console.log("image path: "+imagePath);
  (async () => {
	  const browser = await puppeteer.launch();
	  const page = await browser.newPage();
	  await page.goto(url).catch(err => {
		console.error(err);
		return err; 
	  });
	  await page.screenshot({path: imagePath});
	  
	  //file upload
	  await createFile(imagePath, fileName, callback);
	  
	  await browser.close();
	})();

}

module.exports = screenCapturer;