FROM openjdk:10-jre-slim

EXPOSE 8080

WORKDIR /service
COPY target/BFS-Fransi-0.0.1-SNAPSHOT.jar service.jar

# Run the jar file
CMD java -jar service.jar