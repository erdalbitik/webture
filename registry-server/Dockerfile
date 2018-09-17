FROM openjdk:8u181-jre-slim-stretch

ADD ./target/registry-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/registry-server.jar"]

HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8762/actuator/health || exit 1

EXPOSE 8762