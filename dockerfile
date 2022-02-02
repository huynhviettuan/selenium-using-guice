FROM maven:3.6.1-jdk-8-alpine
RUN apk add curl jq
WORKDIR /app/
COPY pom.xml /app/
COPY src /app/src/
COPY res /app/res/
COPY healthcheck.sh /app/
ENTRYPOINT  sh healthcheck.sh