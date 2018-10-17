FROM openjdk:11-jre-slim

RUN mkdir /app

WORKDIR /app

#ADD ./api/target/

EXPOSE 8080

#CMD java -jar