FROM adoptopenjdk:11-jre-hotspot

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} getir-book.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","/getir-book.jar"]