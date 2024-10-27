package io.github.vacxe.airportinfo

import io.github.vacxe.airportinfo.data.Data
import io.github.vacxe.airportinfo.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.io.files.Path

fun main() {
    Data.bootstrap(Path("/Users/Vacxe/IdeaProjects/airportsinfo/data"))
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureRouting()
}
