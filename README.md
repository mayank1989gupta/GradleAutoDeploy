Project Title
	
	Sample demo application with docker, gradle, spring boot and mongo DB


Pre Requisites
	
	Install docker &, docker-compose
	If running without docker-compose, create the required network for docker containers to communicate.
	
Set Up &, Installation
	
	- Mongo Set up
		Create DB: 
		use rest_tutorial;
	
	adding collection
		db.createCollection("pets");
	
	- Adding data to collection	
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
	
	- To connect Spring Boot &, MongoDB
		As the Mongo DB is locally hosted and the app is hosted on docker container so it will not be access the localhost on the computer.
		To connect MongoDB and App, follow the steps:
		#Create a network: 
		docker network create gradle_docker_net --> [spring_demo_net - Network Name]
		
		#Goto the mongo data directory. For eg:
		C:\data\db
		
		#Run the mongo container
		docker run --name gradle_docker-mongo --network=gradle_docker_net -v mongo-storage:/var/lib/mongo mongo  
		
		Where: 
		gradle_docker-mongo --> Name of the container
		gradle_docker_net --> Name of the network created
		
		#with the above steps the mongo is hosted as docker container
	
	- To stop and remove the existing running container
		docker stop gradle_docker_app
		docker container rm gradle_docker_app
	
	- To build 
		gradle build docker
	
	- To create and run the docker image
		
		docker run --name gradle_docker_app --network=gradle_docker_net -p 8000:8000 -p 8001:8001 -t springio/gs-rest-service	
		Where: 
		gradle_docker_app --> container name of the app
		gradle_docker_net --> Network name
		springio/gs-rest-service --> image name

Running the tests
	
	gradle build runs the created test.
	To run build without test --> use '-x test' in the command.

Additional Component
	
	- Integrating Sonar
		Entries added in build.gradle: 
		plugins {
		  id "org.sonarqube" version "2.6.2"
		}
		
		sonarqube {
		    properties {
		        property 'sonar.projectName', 'Gradle Docker - Sonar'
		    }
		}
		
		Command to publish to sonarqube server: gradle sonarqube
		Command to publish &, run app: gradle sonarqube bootrun
		For Maven: mvn sonar:sonar -Dsonar.host.url=http://localhost:9000
		
		To export the report of the issues as csv:
		1. Goto: https://docs.codescan.io/hc/en-us/articles/360012009891-Exporting-your-issues-as-a-CSV-file
		2. Download the jar and place the same into your SonarQube plugins folder at “(your SonarQube directory)/extensions/plugins/“.
		3. Restart Sonar
		4. Under more on dashboard select --> Csv Issue Import
		
		The export excel functionality is also available using the rest api --> SonarHookController
		
	- Adding Swagger UI with SpringFox
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
		
		//To select specific packages
			/*
			 * return new Docket(DocumentationType.SWAGGER_2) .select()
			 * .apis(RequestHandlerSelectors.basePackage("com.test.api"))
			 * .paths(PathSelectors.ant("/greeting**")) .build() .apiInfo(getApiInfo());
			 */
	
	- Adding Prometheus
		Download and install prometheus
		Windows: choco install prometheus
		
		Change the config yml file as per need. For eg:
		
		global config -->
		scrape_interval:     5s 
		evaluation_interval: 5s 
		
		scrape_configs -->
		job_name: 'GradleAutoDeploy'
	    metrics_path: '/prometheus'
	    
	    static_configs
	    targets: ['localhost:8001']
	    
	    start prometheus:
	    Go the installation directory:
	    For eg: C:\ProgramData\chocolatey\lib\prometheus\tools\prometheus-2.2.1.windows-amd64
	    command: prometheus.exe --config.file=prometheus.yml
	    
	    Once the server is up, goto URL: http://localhost:9090
	    
	    Running prometheus with docker in the same network:
	    Check the docs: https://docs.docker.com/config/thirdparty/prometheus/
	    Steps: 
	    -  Click the Docker icon in the toolbar, select Preferences, then select Daemon. Click Advanced.
	    - "metrics-addr" : "127.0.0.1:9323", "experimental" : true
	    - restart docker
	    - create C:\tmp\prometheus.yml
	    - under targets --> targets: ['docker.for.win.localhost:8001']
	    - docker run --name gradle_prometheus --network=gradle_docker_net --mount type=bind,source=C:/tmp/prometheus.yml,destination=/etc/prometheus/prometheus.yml --publish published=9090,target=9090,protocol=tcp prom/prometheus
	    - URL: localhost:9090
	    
	- Grafana
		Download and install grafana
		Windows: choco install grafana
		
		Goto the bin: C:\ProgramData\chocolatey\lib\grafana\tools\grafana-5.4.3\bin
		start grafana-server.exe
		grafana will start at port: 3000
		URL: http://localhost:3000
		
		Goto Add data source --> select prometheus and give the necessary details
		Click Save & Test --> DataSource is working !
		
		Create dashboard with relevant details:
		add dashboard and edit and provide the details of data source type of panels you want to display
		
		--> To run grafana with docker:
		- docker volume create grafana-storage
		- docker volume ls
		- docker run -d -p 3000:3000 --name=grafana --network=gradle_docker_net -v grafana-storage:/var/lib/grafana grafana/grafana
		- set up the data source: for eg: http://gradle_prometheus:9090 --> used the docker app name as they are hosted in the same network
		- sample query for dashboard: http_server_requests_seconds_count{exception="None",instance="docker.for.win.localhost:8001",job="GradleAutoDeploy",method="GET",status="200",uri="/greeting"}
	
	- Spring Profile
		Added new task for Dev &, Prod in build.gradle to test profiling.
	
Deployment using docker-compose
	
	command: 
	- docker-compose up --> will bring up MongoDB and Application, prometheus, grafana, jenkins
	- docker-compose up -d --> detached mode of above command
	- docker-compose down --> to stop and remove the containers.
Built With
	
	Gradle, spring boot, mongoDB, gradle

Versioning
	
	Demo Version

Authors
	
	Mayank Gupta

License
	
	-NA

Acknowledgements
	
	-NA.
