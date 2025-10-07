FROM openjdk:26-slim-bookworm
WORKDIR /app
COPY target/library-management.jar library-management.jar
ENTRYPOINT [ "java", "-jar", "library-management.jar" ]