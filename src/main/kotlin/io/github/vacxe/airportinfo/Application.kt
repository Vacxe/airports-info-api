package io.github.vacxe.airportinfo

import io.github.vacxe.airportinfo.data.Data
import io.github.vacxe.airportinfo.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    val host = "0.0.0.0"
    val port = 8080
    Data.bootstrap()

    println("Test: http://$host:$port/getAirportsInfo?icaos=YBBN")

    embeddedServer(Netty, port = port, host = host, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureRouting()
}
