#    Phonebook Application
This project was created as a way to solidify and build on existing Kotlin knowledge in response to a job application task.
While building this project I encountered a number of problems which I've to a large extent been able to overcome.
I couldn't solve one in particular - a problem I encounter in my test class for my Controller layer.
I'm still continuing search for the solution to this problem. I'm a TDD guy and not been able to write test cases for my
controller class bugs me. However enjoy reading through.

### Languages: Kotlin
### Framework: Spring, Hibernate

### Technologies
Java 17  
Spring Boot(rest api)   
Maven(build automation tool)  
Lombok(eliminates boilerplate code)    
Hibernate(ORM/code-first approach)      
MySql (relational database)   
H2 (relational in app database used for testing)   
Mockitoo (unit testing/mocking layers)  
Mockk (unit testing/mocking layers)  
Spring JPA(transactions) 
Docker(containerization)    
Mockaroo (fake data api)  

### To run the app locally
Pull the project.
Create a database named "contacts" in MySql.  
Create a .env file at the root level of the project directory.  
Open src>main>resources>"application-prod.properties" file.
Note the following:
```
spring.jpa.hibernate.ddl-auto=create  
pring.datasource.username=${MYSQL_USER}
spring.datasource.password=${MYSQL_PASSWORD}
spring.datasource.url=jdbc:mysql://${DOCKER_HOST}:3306/${MYSQL_DATABASE}?createDatabaseIfNotExist=true
```
Go back to your .env file and create variables to mirror the following:
```
MYSQL_USER=[put your MySql username here(probably root)]
MYSQL_PASSWORD=[put your MySql password here]
MYSQL_DATABASE=contacts
```
Now we can run our our app on localhost. Just start the main method in our main class.  

### To run as a container
Since I didn't use docker compose:  
First, we'll need to define a network to run our 2 services containers  
Second, provide an image for our db
Third, convert our app to a docker image
Fourth, run (or containerize) both image in separate containers but on same network

one for phonebook app and the other for mysql db the app will need to use.

Please follow the following instructions in order carefully:
1. cd to the directory of the folder
2. Create a network to run our containers on using the command below
```
docker network create [specify network name] (e.g docker network create phonebook-mysql-net)
```
3. Pull MySql image from public repository
```
docker pull [specify image]:[specify tag] (e.g. docker pull mysql:8.0)
```
4. Next let's edit our .env file.  
  a. Add field MYSQL_ROOT_PASSWORD key and provide a value as your root user password.  
  b. Next edit the MY_SQL_USER key value. If the value is root, change it to - say your name. Docker will complain if it is root.
  c. Add a new field MY_SQL_DATABASE and provice a value for it preferably existing name.
```
MYSQL_ROOT_PASSWORD={specify password]
MYSQL_USER=[specify a name other than root]
```
5. Run the next command, to containerize and start the mysql service.
```
docker run --name [sprecify a name for your container] --network [specify your network name] --env-file=.env -d mysql:5.7
(e.g docker run --name contacts_db --network phonebook-mysql-net --env-file=.env -d mysql:5.7
```
 
Create a Dockerfile at the root of the project directory.  
This project uses Java 17 
If you have data and space to spare on you PC,  
Add the following to it:
```
From alpine/latest
WORKDIR /app
COPY target/Phonebook-1.jar /app/phonebook-1.jar
MAINTAINER "Adeshola"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/phonebook-1.jar"]
```

