# Usa una imagen base de Java con Gradle preinstalado
FROM gradle:7.6.0-jdk17 AS build

# Configura el directorio de trabajo en el contenedor de compilación
WORKDIR /app

# Copia los archivos del proyecto al contenedor de compilación
COPY . .

# Ejecuta el comando de compilación de Gradle para generar el JAR
RUN gradle build

# Usa una imagen base de Java para el contenedor final
FROM openjdk:17-jdk-alpine

# Configura el directorio de trabajo en el contenedor final
WORKDIR /app

# Copia el archivo JAR generado desde el contenedor de compilación
COPY --from=build /app/build/libs/mutante-0.0.1-SNAPSHOT.jar /app/mutante.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "/app/mutante.jar"]
