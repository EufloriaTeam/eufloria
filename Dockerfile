FROM openjdk:17-slim
COPY . /app
WORKDIR /app
RUN chmod +x gradlew
RUN ./gradlew build -x test 2>&1
RUN ls build/libs
EXPOSE 8080
ENTRYPOINT ["java", "-jar","build/libs/eufloria-0.0.1-SNAPSHOT.jar"]