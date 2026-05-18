# En config-server/Dockerfile
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar pom.xml padre y dependencias
COPY pom.xml .
COPY eureka-server/pom.xml eureka-server/

# Copiar el módulo específico
COPY eureka-server/src eureka-server/src

# Compilar
WORKDIR /app/eureka-server
RUN mvn clean package -DskipTests

# Extraer capas para optimización
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/eureka-server/target/*.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "-Xms128m", "-Xmx256m", "app.jar"]