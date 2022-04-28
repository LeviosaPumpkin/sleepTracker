FROM openjdk:17-alpine
EXPOSE 8082
ADD auth/build/libs/auth-0.1-all.jar auth-0.1-all.jar
ENTRYPOINT ["java","-jar","auth-0.1-all.jar"]