# Base Image
FROM eclipse-temurin:21-jdk

# Working Directory
WORKDIR /app

# Copy JAR
COPY target/*.jar backend_mat.jar

# Expose Application Port
EXPOSE 8888

# Run Application
ENTRYPOINT ["java","-jar","backend_mat.jar"]