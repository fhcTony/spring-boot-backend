FROM openjdk:11-jre

MAINTAINER fuhongchao

VOLUME /tmp

ADD authentication-server-1.0.0-SNAPSHOT.jar app.jar

RUN bash -c 'touch /app.jar'

RUN cp /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime

RUN echo "Asia/Shanghai" > /etc/timezone

EXPOSE 8081

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]