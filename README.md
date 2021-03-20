
what is needed java 11 and maven higher then 3.6 and mysql connection (data base will be created automatically)
how to prepare for deployment
1 adapt application.yml property for database connection. (server port is 8080 check if is free)
2 run command: mvn clean install -U
3 test will be executed if you want to skip -DskipTests  
4 after that you will have jar file in target folder 

deployment
now you can start application with command java -jar "atm-0.0.1-SNAPSHOT.jar"


server port is 8080

swagger is located at http://localhost:8080/swagger-ui/index.html#/


default user is saved: username: sava password: 12345

authentication method basic