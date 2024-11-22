FROM openjdk:17-jdk-slim

ARG VERSION

RUN apt-get update
RUN apt-get install -y wget unzip

# Install released Version from artefacts
RUN wget -q "https://github.com/Vacxe/airports-info-api/releases/download/$VERSION/airports-info-api.tar"
RUN tar -xvf "airports-info-api.tar" -C /usr/local
RUN rm "airports-info-api.tar"

ENV PATH="${PATH}:/usr/local/airports-info-api/bin/"

EXPOSE 8080

ENTRYPOINT ["airports-info-api"]
