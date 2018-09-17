print('dump start');

db.createCollection("fileProcess");

db.fileProcess.insertMany([
  {
  	"messageId":"1",
    "createDate": new Date(),
    "processDate": new Date(),
    "fileType": "image",
    "captureUrl": "www.google.com",
    "fileUrl": "https://files.eprints.org/images/files-logo.jpg",
    "resultCode": "200",
    "resultDescription": "Successfully Uploaded!",
  },
  {
    "messageId":"2",
    "createDate": new Date(),
    "processDate": new Date(),
    "fileType": "pdf",
    "captureUrl": "www.google.com",
    "fileUrl": "http://www.pdf995.com/samples/pdf.pdf",
    "resultCode": "200",
    "resultDescription": "Successfully Uploaded!",
  },
  {
    "messageId":"3",
    "createDate": new Date(),
    "processDate": new Date(),
    "fileType": "image",
    "captureUrl": "www.goog.com",
    "fileUrl": "",
    "resultCode": "500",
    "resultDescription": "Website page not found!",
  }
]);

print('dump complete');
