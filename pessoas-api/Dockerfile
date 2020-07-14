FROM gradle as BUILD
WORKDIR /tmp/
COPY . /tmp
RUN gradle build -x test

FROM openjdk:8-alpine3.9 AS SV
COPY --from=BUILD /tmp/build/libs/*.jar app/pessoas-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "app/pessoas-app.jar"]