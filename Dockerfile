#FROM adoptopenjdk:11-jre-hotspot
#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} /app/authkpi.jar
#ENTRYPOINT ["java", "-jar", kpisagile.jar]
#EXPOSE 8080

FROM openjdk:11
ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} kpisagile.jar
#RUN mvn clean install
COPY /target/${JAR_FILE} agilekpiskit.jar
RUN apt-get update && apt-get -y install tzdata
ENV TZ="Africa/Tunis"
RUN date
ENTRYPOINT ["java", "-jar","agilekpiskit.jar"]
EXPOSE 8081


