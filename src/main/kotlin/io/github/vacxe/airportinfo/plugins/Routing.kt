package io.github.vacxe.airportinfo.plugins

import com.google.gson.Gson
import io.github.vacxe.airportinfo.data.Data
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/getAirportsInfo") {
            val icaos = call.queryParameters["icaos"]?.split(",")
            val airports = icaos?.map { Data.airports[it] }
            if(airports != null) {
                call.respondText(Gson().toJson(airports))
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
