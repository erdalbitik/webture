var screenCapturer = function (puppeteer, url, path) {
  
  (async () => {
	  const browser = await puppeteer.launch();
	  const page = await browser.newPage();
	  await page.goto(url).catch(err => {
		console.error(err);
		return err; 
	  });
	  await page.screenshot({path: path});

	  await browser.close();
	})();

}

module.exports = screenCapturer;