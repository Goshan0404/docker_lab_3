FROM gradle:7.6.0-jdk17-alpine as builder
WORKDIR /opt/app
ENV GRADLE_USER_HOME=/gradle_cache
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
RUN ./gradlew dependencies --no-daemon || return 0
COPY src ./src
RUN ./gradlew bootJar -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /opt/app
RUN addgroup -S appgroup && adduser -S appuser -g appgroup
COPY --from=builder /opt/app/build/libs/*.jar /opt/app/app.jar
RUN chown appuser:appgroup /opt/app/app.jar

RUN rm -rf /var/cache/apk/*
USER appuser

ENV JAVA_OPTS="-Xmx512m" \
    SPRING_DATASOURCE_URL="jdbc:postgresql://my_db:5432/my_db" \
    SPRING_DATASOURCE_USERNAME="admin" \
    SPRING_DATASOURCE_PASSWORD="123"

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /opt/app/app.jar"]
