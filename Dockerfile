FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Definimos el argumento
ARG SERVICE_FOLDER

# Copiar el POM raíz e INSTALARLO
COPY pom.xml .
RUN mvn install -N -DskipTests

# Copiar e INSTALAR el módulo shared
COPY shared/pom.xml shared/
COPY shared/src shared/src
RUN mvn install -f shared/pom.xml -DskipTests

# Copiar el módulo que queremos compilar
COPY ${SERVICE_FOLDER}/pom.xml ${SERVICE_FOLDER}/
COPY ${SERVICE_FOLDER}/src ${SERVICE_FOLDER}/src

# COMPILAR directamente desde la carpeta del servicio
WORKDIR /app/${SERVICE_FOLDER}
RUN mvn package -DskipTests

# Etapa de Ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
ARG SERVICE_FOLDER
COPY --from=build /app/${SERVICE_FOLDER}/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "-Xms128m", "-Xmx256m", "app.jar"]