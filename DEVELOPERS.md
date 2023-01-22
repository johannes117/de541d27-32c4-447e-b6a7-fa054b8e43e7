# Working with the solution
## Getting started
Clone the repository

Run 
```
./gradlew clean build to build the project
```
Run
```
./gradlew bootRun to start the application
```
### Dependencies
- Java 8
- Gradle 6.7
- Spring Boot 2.4.0
- MS SQL Database

## Project structure
The project is structured into the following packages:

- co.zip.candidate.userapi.controller: Contains all the REST endpoints for the User and Account resources
- co.zip.candidate.userapi.domain: Contains the User and Account classes that represent the data model
- co.zip.candidate.userapi.repository: Contains the repository interfaces for User and Account resources
- co.zip.candidate.userapi.services: Contains the service classes that handle business logic and interact with the repositories

## User
### Creating a User
To create a User, make a POST request to /users with a JSON payload containing the following fields:

- name (string, required)
- email (string, required, must be unique)
- monthlySalary (decimal, required)
- monthlyExpenses (decimal, required)

### Retrieving a User
To retrieve a User, make a GET request to /users/{id}.

### List Users
To list all Users, make a GET request to /users

## Account
### Creating an Account
To create an Account, make a POST request to /users/createAccount with a JSON payload containing the following fields:

- accountNumber (string, required)
- accountType (string, required)
- userId (long, required)

### List Accounts by User
To list Accounts by user, make a GET request to /users/{id}/accounts replacing id with your user id. 