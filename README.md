

#Mongo Set up
Create DB: 
use rest_tutorial;

#adding collection
db.createCollection(“pets”);

#Adding data to collection
db.pets.insertMany([
  {
    “name” : “Spot”,
    “species” : “dog”,
    “breed” : “pitbull”
  },
  {
    “name” : “Daisy”,
    “species” : “cat”,
    “breed” : “calico”
  },
  {
    “name” : “Bella”,
    “species” : “dog”,
    “breed” : “australian shepard”
  }
]);

#To connect Spring Boot &, MongoDB
As the Mongo DB is locally hosted and the app is hosted on docker container so it will not be access the localhost on the computer.
To connect MongoDB and App, follow the steps:
	#Create a network: 
	docker network create spring_demo_net --> [spring_demo_net - Network Name]
	
	#Goto the mongo data directory. For eg:
	C:\data\db
	
	#Run the mongo container
	docker run --name spring-demo-mongo --network=spring_demo_net -v C:/data/db -d mongo  
	
	Where: 
	spring-demo-mongo --> Name of the container
	spring_demo_net --> Name of the network created
	
	#with the above steps the mongo is hosted as docker container

# To build 
gradle build docker

#To create and run the docker image
docker run --name spring-demo --network=spring_demo_net -p 9000:9000 -p 9001:9001 -t springio/gs-rest-service	

	Where: 
	spring-demo --> container name of the app
	spring_demo_net --> Network name
	springio/gs-rest-service --> image name