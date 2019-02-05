FROM openjdk:8-jdk
MAINTAINER Mayank Gupta mayank1989gupta@gmail.com
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
#spring-demo-mongo --> mongo container name, rest_tutorial --> DB name in mongo
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://gradle_docker-mongo/rest_tutorial","-cp","app:app/lib/*","com.test.api.Application"]