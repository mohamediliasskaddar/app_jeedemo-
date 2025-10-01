#dckerFile
# Étape 1 : Build Maven (optionnel si tu veux builder dans Docker)
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Runtime Tomcat
FROM tomcat:10.1-jdk21
COPY --from=builder /app/target/jeedemo.war  /usr/local/tomcat/webapps/jeedemo.war
EXPOSE 8080
CMD ["catalina.sh", "run"]


