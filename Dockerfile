FROM eclipse-temurin:23-jdk

ARG RELEASE_VERSION

# Install dependencies
RUN apt-get update
RUN apt-get install -y wget unzip

# Install Release
RUN wget -q "https://github.com/Vacxe/airports-info-api/releases/download/$RELEASE_VERSION/airports-info-api.tar" && \
    tar -xvf "airports-info-api.tar" -C /usr/local &&  \
    rm "airports-info-api.tar"

ENV PATH="${PATH}:/usr/local/airports-info-api/bin/"

EXPOSE 8080

ENTRYPOINT ["airports-info-api"]
