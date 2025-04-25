# Build
# https://github.com/carlossg/docker-maven/blob/8cfe24baffa5b250f7bb2d31ce233fc28f3c4f20/amazoncorretto-21/Dockerfile
FROM maven:3.9.9-amazoncorretto-21-alpine AS build
WORKDIR /app
COPY ./src .
COPY ./pom.xml .
RUN mvn clean package

# Deploy
FROM amazoncorretto:21-alpine3.21 AS deploy
WORKDIR /app
RUN mkdir target

LABEL maintainer="Rishi Raj"
LABEL version="1.0"
LABEL description="Dockerfile for Order-Manager::Product with JDK 21 and MongoDB"

COPY --from=build /app/target/product-1.0.1-SNAPSHOT.jar /app/target/
EXPOSE 8080
CMD [ "java", "-jar","target/product-1.0.1-SNAPSHOT.jar" ]


VOLUME ./data:/data/db
