# URL Lookup Service

This application exposes an API to lookup if a URL is malicious.
The project is built in Java, Spring Boot and uses Redis as the datastore.

### Instructions to run using Docker Compose
```
  ./mvnw install dockerfile:build
  docker-compose up -d
```

### Instructions to run without Docker ( Requires Redis to be running locally)
```
./mvnw package && java -jar target/urllookupservice-0.0.1-SNAPSHOT.jar
```
