

#Mongo Set up
	Create DB: 
	use rest_tutorial;

#adding collection
	db.createCollection("pets");

#Adding data to collection	
	db.pets.insertMany([
	  {
	    "name" : "Spot",
	    "species" : "dog",
	    "breed" : "pitbull"
	  },
	  {
	    "name" : "Daisy",
	    "species" : "cat",
	    "breed" : "calico"
	  },
	  {
	    "name" : "Bella",
	    "species" : "dog",
	    "breed" : "australian shepard"
	  }
	]);

#To connect Spring Boot &, MongoDB
	As the Mongo DB is locally hosted and the app is hosted on docker container so it will not be access the localhost on the computer.
	To connect MongoDB and App, follow the steps:
		#Create a network: 
		docker network create gradle_docker_net --> [spring_demo_net - Network Name]
	
	#Goto the mongo data directory. For eg:
	C:\data\db
	
	#Run the mongo container
	docker run --name gradle_docker-mongo --network=gradle_docker_net -v C:/data/db -d mongo  
	
	Where: 
	gradle_docker-mongo --> Name of the container
	gradle_docker_net --> Name of the network created
	
	#with the above steps the mongo is hosted as docker container

# To build 
	gradle build docker

#To create and run the docker image
	docker run --name gradle_docker_app --network=gradle_docker_net -p 9000:9000 -p 9001:9001 -t springio/gs-rest-service	

	Where: 
	gradle_docker_app --> container name of the app
	gradle_docker_net --> Network name
	springio/gs-rest-service --> image name