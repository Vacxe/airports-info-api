package io.github.vacxe.airportinfo.plugins

import com.google.gson.Gson
import io.github.vacxe.airportinfo.data.Data
import io.github.vacxe.airportinfo.tools.Distance
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/getAirportsInfo") {
            val icaos = call.queryParameters["icaos"]?.split(",")
            val airports = icaos?.map { Data.airports[it] }
            if (airports != null) {
                call.respondText(Gson().toJson(airports))
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        get("/nearby") {
            val lat = call.queryParameters["lat"]?.toDoubleOrNull()
            val lng = call.queryParameters["lng"]?.toDoubleOrNull()
            val distance = (call.queryParameters["distance"]?.toDoubleOrNull() ?: 200.0)
                .coerceAtMost(200.0)

            if (lat != null && lng != null) {
                val airports = Data.airports.values.filter {
                    return@filter Distance.distanceBetween(lat, lng, it.latitude_deg, it.longitude_deg) < distance &&
                            it.type.contains("airport") &&
                            (it.runways.isNotEmpty() || it.frequencies.isNotEmpty())
                }.sortedBy {
                    Distance.distanceBetween(lat, lng, it.latitude_deg, it.longitude_deg)
                }

                if (airports.isNotEmpty()) {
                    call.respondText(Gson().toJson(airports))
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}
