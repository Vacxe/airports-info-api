FROM openjdk:17-jdk-slim

ARG VERSION

RUN apk update && \
    apk add --no-cache jq wget unzip

# Install released Version from artefacts
RUN wget -q "https://github.com/Vacxe/airports-info-api/releases/download/VERSION/airports-info-api.tar" && \
    tar -xvf "airports-info-api.tar" -C /usr/local &&  \
    rm "airports-info-api.tar"

ENV PATH="${PATH}:/usr/local/airports-info-api/bin/"

EXPOSE 8080

ENTRYPOINT ["airportsinfo"]
