#########
# Build #
#########

# https://github.com/carlossg/docker-maven/blob/8cfe24baffa5b250f7bb2d31ce233fc28f3c4f20/amazoncorretto-21/Dockerfile
#  gradle:jdk21-corretto-al2023

# https://hub.docker.com/_/gradle
# https://github.com/gradle/docker-gradle/blob/ed0ea425043215ae106a8240ee86cb5f5e1ca5fb/jdk25-alpine/Dockerfile
#FROM gradle:9.2.1-jdk25-alpine AS build
#WORKDIR /app
#COPY ./src ./build.gradle.kts ./
#RUN gradle build

##########
# Deploy #
##########
# FROM amazoncorretto:21-alpine3.21 AS deploy


# openjdk 21 amazoncorretto - Official Image | Docker Hub
# https://hub.docker.com/_/amazoncorretto

# corretto-docker/25/jdk/alpine/3.22/Dockerfile at 5cf76cde46d4c2293a5d709e1e8630a334cb61c5 · corretto/corretto-docker
# https://github.com/corretto/corretto-docker/blob/5cf76cde46d4c2293a5d709e1e8630a334cb61c5/25/jdk/alpine/3.22/Dockerfile
FROM amazoncorretto:25.0.1-alpine AS deploy
WORKDIR /app
COPY ./src ./build.gradle.kts ./gradlew ./
RUN mkdir gradle
COPY ./gradle ./gradle/
RUN echo ${PWD} && ls -lR

RUN ./gradlew build

#WORKDIR /app
#RUN mkdir target
#LABEL maintainer="Rishi Raj"
#LABEL version="2.0"
#LABEL description="Dockerfile for Order-Manager::Product with Kotlin 1.9 (JDK 21) and MongoDB"

#COPY --from=build /app/build/libs/app-0.0.1-SNAPSHOT.jar /app/target/
RUN echo ${PWD} && ls -lR
EXPOSE 8080
CMD [ "java", "-jar", "./build/libs/app-0.0.1-SNAPSHOT.jar" ]

VOLUME ./productdb:/data/db