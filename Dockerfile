FROM openjdk:14
ADD target/neardy-0.0.1-SNAPSHOT.jar neardy-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "neardy-0.0.1-SNAPSHOT.jar"]