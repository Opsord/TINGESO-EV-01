FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} TopEducationApp-BETA.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/TopEducationApp-BETA.jar"]