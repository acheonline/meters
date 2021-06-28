FROM adoptopenjdk:11-jre-hotspot
COPY ./target/meters-parent-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch meters-parent-1.0-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","meters-parent-1.0-SNAPSHOT.jar"]