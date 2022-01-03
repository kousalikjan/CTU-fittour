FROM amazoncorretto:17

COPY build/libs/fittour-*.jar /app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
