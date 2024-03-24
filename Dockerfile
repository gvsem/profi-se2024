FROM gradle:7.2.0-jdk17-alpine
COPY . /home/gradle/src
WORKDIR /home/gradle/src

EXPOSE 9024
CMD gradle bootRun -x test --no-daemon