FROM openjdk:8-jdk
MAINTAINER Mayank Gupta mayank1989gupta@gmail.com
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 27017
EXPOSE 8000
EXPOSE 8001
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://gradle_docker-mongo:27017/rest_tutorial","-cp","app:app/lib/*","com.test.api.Application"]
