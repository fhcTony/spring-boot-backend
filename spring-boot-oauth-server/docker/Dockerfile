FROM openjdk:8-jre

MAINTAINER fuhongchao

VOLUME /tmp

ADD spring-boot-oauth-server-1.0.0-SNAPSHOT.jar app.jar

RUN bash -c 'touch /app.jar'

EXPOSE 8081

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]