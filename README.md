# BFS-Fransi
# Building
Account Transaction Task notes the application Simple Spring boot app for User Acount Transaction.

using Spring Boot , Kotlin , Rest Apis , H2 as the DB!, swagger API , Junit .

application services :

BSF_Fransi : 
runs on port 8080 steps :

1.prepare your enviroment if you work locally or on a production.

2.clone the git repository

3.setup java idea (intelij,etc)

4.import the BSF_Fransi microservice in the ide and download dependencies.

5.run the microservice.

you can now work with the rest apis.


## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Application Framework
* [Swagger](https://swagger.io/) - API Documentation
* [Apache Maven](https://maven.apache.org/) - Software Project Management
* [H2 Database Engine](https://www.h2database.com/html/main.html) - In-Memory Database
* [JUnit 5](https://junit.org/junit5/) - Unit Testing Framework Coverage (87%)


## Maven
```sh
$ ./mvnw clean package
```
## Docker
```sh
$ docker build -t {any}/BFS-Fransi -f Dockerfile .

$ docker run -p 8080:8080 {any}/BSF-Fransi
```

# Running
These apis implement user-management functionality, where user can create account , get account details through APIs.

# End Point :

1) localhost:8080//protected/v1/account   
   User can create an account sample Json for /create account (POST):
   
   JSON Request: { "accountName":"ali", "openingDeposit": 100, "accountType":"SAVINGS" }

2) localhost:8080/protected/v1/account/{id}   
   User Can get account details by account Id (GET).

3) localhost:8080/protected/v1/account/{id}/transfer 
   User transfer money from his account to another account(POST).  
   JSON Request:  { "toAccountId":"1", "amount": 50 }


### API Documentation
You can follow `/swagger-ui.html` to find all information about APIs.
