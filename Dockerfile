FROM openjdk:17-jdk-slim

COPY build/distributions/airportsinfo.tar /

RUN tar -xvf "airportsinfo.tar" -C /usr/local

ENV PATH="${PATH}:/usr/local/airportsinfo/bin/"

EXPOSE 8080

ENTRYPOINT ["airportsinfo"]
