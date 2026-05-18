# En config-server/Dockerfile
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar pom.xml padre y dependencias
COPY pom.xml .
COPY api-gateway/pom.xml api-gateway/

# Copiar el módulo específico
COPY api-gateway/src api-gateway/src

# Compilar
WORKDIR /app/api-gateway
RUN mvn clean package -DskipTests

# Extraer capas para optimización
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/api-gateway/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Xms128m", "-Xmx256m", "app.jar"]