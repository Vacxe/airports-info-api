package io.github.vacxe

import io.github.vacxe.airportinfo.data.Data
import io.github.vacxe.airportinfo.models.Airport
import io.github.vacxe.airportinfo.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testGetAirportsInfo() = testApplication {
        application {
            Data.bootstrap()
            configureRouting()
        }
        client.get("/getAirportsInfo") {
            parameter("icaos", "YBBN")
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            val airports : List<Airport> = Json.decodeFromString(bodyAsText())
            assertEquals(airports.size,1)
        }
    }
}
