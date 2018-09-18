#FROM selenium/node-firefox:3.14.0-europium
#LABEL authors=SeleniumHQ

#USER root

#====================================
# Scripts to run Selenium Standalone
#====================================
#COPY entry_point.sh /opt/bin/entry_point.sh

FROM openjdk:8u181-jre-slim-stretch

ADD ./target/consumer.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/consumer.jar"]

#HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8888/actuator/health || exit 1


EXPOSE 8098