# Set up builder stage
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /build

COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY gradle ./gradle
COPY src ./src

RUN chmod +x ./gradlew

RUN ./gradlew clean bootJar -x test

# Set up final stage
FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY --from=builder build/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


