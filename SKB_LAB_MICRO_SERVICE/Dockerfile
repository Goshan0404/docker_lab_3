FROM gradle:7.6.0-jdk17 as builder
WORKDIR /opt/app
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
COPY src ./src
RUN ./gradlew bootJar -x test

FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/build/libs/*.jar /opt/app/app.jar
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
