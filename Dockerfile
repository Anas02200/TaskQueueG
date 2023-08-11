FROM openjdk:19-jdk-alpine



USER root

COPY */target/queueServer-1.0-SNAPSHOT-jar*.jar queueServer-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/queueServer-1.0-SNAPSHOT.jar"]