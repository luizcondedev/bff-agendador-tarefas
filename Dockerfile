FROM gradle:9.5-jdk17 as build
WORKDIR /app
COPY . .
RUN ./gradlew clean build --no-daemon --refresh-dependencies

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar bff-agendador-tarefas.jar

EXPOSE 8083

CMD ["java", "-jar", "/app/bff-agendador-tarefas.jar"]