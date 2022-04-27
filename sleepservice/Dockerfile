FROM openjdk:17-alpine
ADD sleepservice/build/libs/sleepservice-0.1-all.jar sleepservice-0.1-all.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sleepservice-0.1-all.jar"]
