#FROM adoptopenjdk:11-jre-hotspot
#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} /app/authkpi.jar
#ENTRYPOINT ["java", "-jar", authkpi.jar]
#EXPOSE 8080

FROM openjdk:11
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} authkpi.jar
ENTRYPOINT ["java", "-jar","authkpi.jar"]
EXPOSE 8080


