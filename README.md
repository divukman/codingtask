# SearchMetrics task

The purpose of this project is to create a service that constantly checks the currency exchange rate from Bitcoin to US-Dollar (1Bitcoin = x USD).


## Implementation

The service was created using Spring framework, with Spring Boot 2. 

Exchange rates API used in this project is:
https://www.blockchain.com/api/exchange_rates_api

This API returns JSON object with multiple currency values. This project only uses the US currency and stores it inside a database. If project was to be deployed live, then H2, in memory, database should be replaced with NoSQL database, such as MongoDB.


## Configuration
Application will pool the bitcoin API every N milliseconds, where the number is configurable via `'appconfig.check.period.millis'` property. The currency (could be configurable) is set via `'appconfig.blockchain.api.USD'` property.

Default behavior is to pool every 10 seconds for USD exchange rates. Rates are saved in the local database for later consumption via endpoints.



## REST API

The REST API is described below.

### Get latest rate

#### Request

`GET /api/v1/currency`

    curl -i -H "Accept: application/json" http://localhost:8080/api/v1/currency/

#### Response

    HTTP/1.1 200
    Vary: Origin
    Vary: Access-Control-Request-Method
    Vary: Access-Control-Request-Headers
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Thu, 06 Jun 2019 15:19:14 GMT

    {"last":7716.76,"buy":7716.76,"sell":7716.76,"symbol":"$","date":"2019-06-06T15:19:06.935+0000","name":"USD","15m":7716.76}


### Get historical rates

#### Request

`GET /api/v1/currency/getAllInRange`

    curl -i -H "Accept: application/json" "http://localhost:8080/api/v1/currency/getAllInRange?from=2019-06-06&to=2019-06-06"

#### Response

    HTTP/1.1 200
    Vary: Origin
    Vary: Access-Control-Request-Method
    Vary: Access-Control-Request-Headers
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Thu, 06 Jun 2019 15:22:05 GMT

    [{"last":7707.38,"buy":7707.38,"sell":7707.38,"symbol":"$","date":"2019-06-06T15:21:47.089+0000","name":"USD","15m":7707.38},{"last":7707.38,"buy":7707.38,"sell":7707.38,"symbol":"$","date":"2019-06-06T15:21:57.053+0000","name":"USD","15m":7707.38}]


### No data
If no data is present in the database, following JSON will be returned on all endpoints:

    HTTP/1.1 200
    Vary: Origin
    Vary: Access-Control-Request-Method
    Vary: Access-Control-Request-Headers
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Thu, 06 Jun 2019 15:25:39 GMT
    {"info":"No data."}



## Run the project

`mvn package && mvn spring-boot:run`

## To do ideas
* Do not save exchange rates if state has not changed
* Use MongoDB
* Add other currencies?
* Write unit tests
* ...