#########
# Build #
#########

# https://github.com/carlossg/docker-maven/blob/8cfe24baffa5b250f7bb2d31ce233fc28f3c4f20/amazoncorretto-21/Dockerfile
#FROM gradle:jdk21-corretto-al2023 AS build
#WORKDIR /app
#COPY ./src ./build.gradle.kts ./
#RUN gradle build

##########
# Deploy #
##########

FROM amazoncorretto:21-alpine3.21 AS deploy
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