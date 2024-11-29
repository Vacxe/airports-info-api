# Airport Info API wrapper

### How to use
* Pull docker image `docker pull ghcr.io/vacxe/airports-info-api:1.0.0`
* Run Docker `docker run -p 8080:8080 vacxe/airports-info-api:1.0.0`
* When initalized try to call `http://localhost:8080/getAirportsInfo?icaos=YBBN`
* You can call multiple ICAOs in single requiest `http://localhost:8080/getAirportsInfo?icaos=YBBN,YBSU,YRED`
