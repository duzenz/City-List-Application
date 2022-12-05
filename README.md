#City list application

### Running the application
1. Open the application in an IDE as a maven application and run it.
2. After it starts you can reach the application from http://localhost:8080
3. Inside root folder there is postman folder which contains city api rest api collection for localhost. You can use it for testing rest endpoints. 

### Api Documentation
After running the application you can chech the api doc from:

    http://localhost:8080/swagger-ui.html

### In-Memory DB
The application uses h2 database. After running the application you can reach the db from:

    http://localhost:8080/h2-console

Config can be found in application.properties file.

### Packaging the application

Create the jar file:

    mvn clean install

Jar file extracted into target folder.


### Testing the application
You can run the unit test and integration tests with maven.

    mvn clean test
