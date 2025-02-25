FROM maven:3.8.1-openjdk-17

VOLUME /tmp

ARG PROJECT_DIR=/

COPY $PROJECT_DIR project/

EXPOSE 8082

WORKDIR project

ENTRYPOINT ["mvn", "spring-boot:run"]