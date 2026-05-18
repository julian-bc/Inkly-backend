# En config-server/Dockerfile
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar pom.xml padre y dependencias
COPY pom.xml .
COPY config-server/pom.xml config-server/

# Copiar el módulo específico
COPY config-server/src config-server/src

# Compilar
WORKDIR /app/config-server
RUN mvn clean package -DskipTests

# Extraer capas para optimización
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/config-server/target/*.jar app.jar

EXPOSE 8888
ENTRYPOINT ["java", "-jar", "-Xms128m", "-Xmx256m", "app.jar"]