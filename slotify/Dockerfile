FROM openjdk:11
ADD target/slotify-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3001
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "/app.jar"]
