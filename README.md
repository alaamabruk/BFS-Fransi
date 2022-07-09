# BFS-Fransi
# 

Account Transaction Task 
notes the application
Simple Spring boot app for User Acount Transaction.

using Spring Boot , Kotlin , Rest Apis , H2 as the DB!, swagger API , Junit .

application services :
 * BSF_Fransi : runs on port 8080
steps
1.prepare your enviroment if you work locally or on a production :
  * clone the git repository
  * setup java idea (intelij,etc)
  * import the BSF_Fransi microservice in the ide and download dependencies.
2. run the microservice.
3. you can now work with the rest apis.



Built With
Kotlin
Spring Boot - Application Framework
Swagger - API Documentation
Apache Maven - Software Project Management
H2 Database Engine - In-Memory Database
JUnit 5 - Unit Testing Framework (Coverage 82%)



Building
Maven
$ ./mvnw clean package

Docker
$ docker build -t {any}/BFS-Fransi -f Dockerfile .

$ docker run -p 8080:8080 {any}/BSF-Fransi


These apis implement user-management functionality, where user can create account , get account details through  APIs


endpoints ( default is POST )
* localhost:8080//protected/v1/account           //User can craete an account
  sample Json  for /create account:
  {
  "accountName":"ali",
  "openingDeposit": 100,
  "accountType":"SAVINGS"
  }

* localhost:8080/protected/v1/account/{id}           //User Can get account details by account Id (GET).

* localhost:8080/protected/v1/account/{id}/transfer          //User transfer money from his account to another account(POST).
  sample Json  for /create transfer:
  {
  "toAccountId":"1",
  "amount": 50
  }
