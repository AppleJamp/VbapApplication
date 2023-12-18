FROM maven:3.9.5-eclipse-temurin-21-alpine AS builder
COPY ./src /usr/src/app/src
COPY ./pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml -DskipTests=true clean package

FROM maven:3.9.5-eclipse-temurin-21-alpine
COPY --from=builder /usr/src/app/target/*.jar app.jar

EXPOSE $SERVER_PORT

ARG PROFILE=dev
ENV SPRING_PROFILES_ACTIVE=$PROFILE

ENTRYPOINT [ "java", "-jar", "app.jar" ]