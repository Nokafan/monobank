"# monobank"
GET http://localhost:8080/

POST http://localhost:8080/order/create

{"routeId":"002", "dateTime":"2019-01-20 16:55"}

GET http://localhost:8080/bid/status?id=1

GET http://localhost:8080/bid/all

http://localhost:8080/bid/process?status=NEW


