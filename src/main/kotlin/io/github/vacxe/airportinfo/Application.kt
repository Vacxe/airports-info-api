package io.github.vacxe.airportinfo

import io.github.vacxe.airportinfo.data.Data
import io.github.vacxe.airportinfo.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.io.files.Path

fun main(args: Array<String>) {
    val dataPath = args[0]
    val host = args[1]
    val port = args[2].toInt()
    Data.bootstrap(Path(dataPath))

    println("Test: http://$host:$port/getAirportInfo?icao=YBBN")

    embeddedServer(Netty, port = port, host = host, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureRouting()
}
