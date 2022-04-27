FROM openjdk:17-alpine
EXPOSE 8761
ADD eureka-server/build/libs/eureka-server-1.0-SNAPSHOT.jar eureka-server-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","eureka-server-1.0-SNAPSHOT.jar"]