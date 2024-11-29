# Airport Info API wrapper

**If this project will have a few sponsors I can deploy it for free access for everyone**

### References
* Based on data from [ourairports.com](https://ourairports.com/)
* Ispired by [airportdb.io](https://airportdb.io/) but they trying to monetize free data from [ourairports.com](https://ourairports.com/)

### How to use
* Pull docker image
  * from Docker Registry `docker pull vacxe/airports-info-api:1.0.0`
  * from GitHub `docker pull ghcr.io/vacxe/airports-info-api:1.0.0`
* Run Docker `docker run -p 8080:8080 vacxe/airports-info-api:1.0.0`
* When initalized try to call `http://localhost:8080/getAirportsInfo?icaos=YBBN`
* You can call multiple ICAOs in single requiest `http://localhost:8080/getAirportsInfo?icaos=YBBN,YBSU,YRED`
* All Contract models can be found [here](https://github.com/Vacxe/airports-info-api/tree/main/src/main/kotlin/io/github/vacxe/airportinfo/models)
  
