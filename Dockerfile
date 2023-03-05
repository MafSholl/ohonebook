# base image to build a JRE
FROM amazoncorretto:17.0.3-alpine as corretto-jdk

# required for strip-debug to work
RUN apk add --no-cache binutils

# Build small JRE image
ENV JAVA_HOME=/jre
RUN $JAVA_HOME/bin/jlink \
         --verbose \
         --add-modules ALL-MODULE-PATH \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /customjre

# main app image
FROM alpine:latest
#ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# copy JRE from the base image
COPY --from=corretto-jdk /customjre $JAVA_HOME


FROM openjdk:11-jdk-alpine
WORKDIR /app
COPY target/Phonebook-1.jar /app/phonebook-1.jar
MAINTAINER "Adeshola"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "phonebook-1.jar"]