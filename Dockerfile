
FROM gradle:8.7-jdk17 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle clean bootJar --no-daemon


FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# 3. Порт и запуск
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]


#docker build -t food-stack-app .
#docker run -p 8080:8080 food-stack-app