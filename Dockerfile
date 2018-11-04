FROM openjdk:11-jre-slim

RUN mkdir /app

WORKDIR /app

#ADD ./api/target/

EXPOSE 8083

#CMD java -jar