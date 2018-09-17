FROM openjdk:8u181-jre-slim-stretch

ADD ./target/config-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-server.jar"]

HEALTHCHECK --interval=15s --timeout=5s CMD curl -f http://localhost:8888/actuator/health

EXPOSE 8888