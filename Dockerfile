# An example Dockerfile if the bootBuildImage buildpack wasn't used.
# Expects the project to already be built.
# See also https://spring.io/guides/topicals/spring-boot-docker/
FROM openjdk:17-jdk-alpine as builder
VOLUME /tmp
COPY build/libs/\*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:17-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /tmp
COPY --from=builder dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
