FROM eclipse-temurin:17-jdk-alpine
COPY /build/libs/notes.jar notes.jar
ENTRYPOINT java -jar notes.jar