# ====== STAGE 1: BUILD ======
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests -Dmaven.test.skip=true package

# ====== STAGE 2: RUNTIME ======
FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S app && adduser -S app -G app && \
    mkdir -p /app /data && \
    chown -R app:app /app /data

WORKDIR /app
USER app

COPY --from=build /app/target/*.jar app.jar

VOLUME ["/data"]

EXPOSE 8080

# Deixe só o que é "default" e seguro aqui
ENV SPRING_PROFILES_ACTIVE=prod \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# (Opcional) Requer spring-boot-starter-actuator
# HEALTHCHECK --interval=30s --timeout=3s --start-period=20s --retries=3 \
#   CMD wget -qO- http://127.0.0.1:8080/actuator/health || exit 1

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
