# Build
# https://github.com/carlossg/docker-maven/blob/8cfe24baffa5b250f7bb2d31ce233fc28f3c4f20/amazoncorretto-21/Dockerfile
FROM maven:3.9.9-amazoncorretto-21-alpine AS build
WORKDIR /app
COPY ./src .
COPY ./pom.xml .
RUN mvn package

# Deploy
FROM amazoncorretto:8-alpine3.21-jre AS deploy
WORKDIR /app
RUN mkdir target

LABEL maintainer="Rishi Raj"
LABEL version="1.0"
LABEL description="Dockerfile for Order-Manager::Gateway with JDK 21 and keycloak"

COPY --from=build /app/target/gateway-1.0.1-SNAPSHOT.jar /app/target/
EXPOSE 9000
CMD [ "java", "-jar","target/gateway-1.0.1-SNAPSHOT.jar" ]
