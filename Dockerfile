#FROM adoptopenjdk:11-jre-hotspot
#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} /app/authkpi.jar
#ENTRYPOINT ["java", "-jar", kpisagile.jar]
#EXPOSE 8080

FROM openjdk:11
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} kpisagile.jar
#COPY /target/${JAR_FILE} kpisagile.jar
ENTRYPOINT ["java", "-jar","kpisagile.jar"]
EXPOSE 8081


