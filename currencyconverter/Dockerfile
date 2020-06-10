FROM openjdk:8-jdk-alpine
RUN mkdir /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/app.jar
RUN chmod -R 775 /app
ENTRYPOINT ["java","-jar","/app/app.jar"]