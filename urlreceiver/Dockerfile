FROM openjdk:8u181-jre-slim-stretch

ADD ./target/urlreceiver.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/urlreceiver.jar"]

#HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8888/actuator/health || exit 1

EXPOSE 8090