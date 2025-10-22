# ========================
# 1. Builder stage
# ========================
FROM gradle:9.1.0-jdk25 AS builder

WORKDIR /build

# Copy file cấu hình Gradle trước để tận dụng cache
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY gradle ./gradle

# Cache dependencies
RUN gradle dependencies --no-daemon || return 0

# Copy source code
COPY src ./src

# Build project (bỏ qua test để nhanh)
RUN gradle clean bootJar -x test --no-daemon

# ========================
# 2. Runtime stage
# ========================
FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY --from=builder /build/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
