FROM openjdk:11
COPY ./build/libs/yogiyogi-0.0.1-SNAPSHOT.jar app.jar
COPY ./src/main/resources/serviceAccountKey.json src/main/resources/serviceAccountKey.json
ENTRYPOINT ["java", "-jar", "app.jar"]
