##########
# Build #
##########

# https://github.com/carlossg/docker-maven/blob/8cfe24baffa5b250f7bb2d31ce233fc28f3c4f20/amazoncorretto-21/Dockerfile
FROM gradle:jdk21-corretto-al2023 AS build
WORKDIR /app
COPY ./src ./build.gradle.kts ./
RUN gradle bootJar

##########
# Deploy #
##########

FROM amazoncorretto:21-alpine3.21 AS deploy
WORKDIR /app
RUN mkdir target

LABEL maintainer="Rishi Raj"
LABEL version="2.0"
LABEL description="Dockerfile for Order-Manager::Order with Kotlin 1.9 (JDK 21), Kafka and MySQL"

COPY --from=build /app/build/libs/app-0.0.1-SNAPSHOT.jar /app/target/
EXPOSE 8081
CMD [ "java","-jar","target/app-0.0.1-SNAPSHOT.jar" ]

VOLUME ./mysql:/var/lib/mysql
VOLUME ./docker/mysql/init.sql:/docker-entrypoint-initdb.d/init.sql
