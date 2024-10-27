package io.github.vacxe.airportinfo.plugins

import com.google.gson.Gson
import io.github.vacxe.airportinfo.data.Data
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/getAirportInfo") {
            val icao = call.queryParameters["icao"]
            val airport = Data.airports[icao]
            call.respondText(Gson().toJson(airport))
        }
    }
}
