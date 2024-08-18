# First stage: Build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Second stage: Create the final image
FROM bellsoft/liberica-openjdk-alpine:21.0.1-cds
WORKDIR /app

COPY --from=build /app/container/target/container-0.0.1-SNAPSHOT.jar /app/myapp.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "sleep 15 && java -jar myapp.jar"]