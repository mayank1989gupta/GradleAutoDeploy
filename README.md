

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

#To stop and remove the existing running container
	docker stop gradle_docker_app
	docker container rm gradle_docker_app

# To build 
	gradle build docker

#To create and run the docker image
	docker run --name gradle_docker_app --network=gradle_docker_net -p 8000:8000 -p 8001:8001 -t springio/gs-rest-service	

	Where: 
	gradle_docker_app --> container name of the app
	gradle_docker_net --> Network name
	springio/gs-rest-service --> image name

#Integrating Sonar
	Entries added in build.gradle: 
	plugins {
	  id "org.sonarqube" version "2.6.2"
	}
	
	sonarqube {
	    properties {
	        property 'sonar.projectName', 'Gradle Docker - Sonar'
	    }
	}
	
#Adding Swagger UI with SpringFox
	One of the most popular API documentation specifications is OpenApi, formerly known as Swagger.
	SpringFox is a swagger integration of Springs:
	"It can automatically inspect your classes, detect Controllers, their methods, model classes they 
	use and URLs to which they are mapped. Without any handwritten documentation, it can generate a lot 
	of information about your API just by inspecting classes in your application. "
	
	Added new config class: SpringFoxConfig.java --> acts as the config class for Swagger and 
	SpringFox.
	Uses the @EnableSwagger2 annotation &, definition of what to document.
	
	Dependencies added: 
	compile "io.springfox:springfox-swagger2:2.9.2"
	compile "io.springfox:springfox-swagger-ui:2.9.2"
    
    Swagger UI URL: http://<IP>:<Port>/swagger-ui.html
    Eg: http://localhost:8000/swagger-ui.html