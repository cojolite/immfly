# immfly

# Building the Docker Images

To build the code as a docker image, open a command-line window change to the directory where you have downloaded the immfly source code.

Run the following maven command.  This command will execute the [Spotify docker plugin](https://github.com/spotify/docker-maven-plugin) defined in the pom.xml file.  

**mvn clean package docker:build**

If everything builds successfully you should see a message indicating that the build was successful.

# Running the services

Now we are going to use docker-compose to start the actual image.  To start the docker image, issue the following docker-compose command:

**docker-compose up**

If everything starts correctly you should see a bunch of Spring Boot information fly by on standard out.  At this point all of the services needed will be running.

Administrator privileges are required to consume the endpoints, the credentials are:

user: immfly
password: 1mmFly

In terminal you can run the following command (You can use Postman):

**curl --request GET localhost:8080/v1/flight-information/EC-MYT/653 -u immfly:1mmFly**


There are others examples you can use.

| Tail Number   |      Flight Number      |
|----------|:-------------:|
| 653 |  EC-MYT |
| 654 |    EC-MYT   |
| 655 | EC-MYT |
| 657 |  EC-MYP |
| 658 |    EC-MYP   |
| 659 | EC-MYP |
