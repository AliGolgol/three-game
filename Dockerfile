FROM maven:3.6.3-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:13-alpine3.10
COPY --from=build /usr/src/app/target/game-of-three.jar /usr/app/game-of-three.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/usr/app/game-of-three.jar"]