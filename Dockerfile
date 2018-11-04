FROM openjdk:11-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./api/target/user-api-1.0-SNAPSHOT.jar /app

EXPOSE 8083

CMD java -jar user-api-1.0-SNAPSHOT.jar