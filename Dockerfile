
FROM eclipse-temurin:17-jre-alpine AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
