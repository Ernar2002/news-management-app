FROM maven:3.8.1-openjdk-17-slim AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package

FROM openjdk:17.0.1-jdk-slim
COPY --from=builder /app/target/strong-team-news-0.0.1-SNAPSHOT.jar /
EXPOSE 8080
CMD ["java", "-jar", "/myapp.jar"]