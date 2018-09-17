# webture
Web page capture

mvn package -DskipTests

docker-compose up --build

GET localhost:8090


POST localhost:8090 { "url":"www.google.com", "fileType":"image" }
