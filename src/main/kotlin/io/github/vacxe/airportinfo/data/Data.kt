package io.github.vacxe.airportinfo.data

import io.github.vacxe.airportinfo.models.Airport
import io.github.vacxe.airportinfo.models.Frequency
import io.github.vacxe.airportinfo.models.NavAid
import io.github.vacxe.airportinfo.models.Runway
import org.jetbrains.kotlinx.dataframe.AnyFrame
import kotlin.time.measureTime
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.io.*
import org.jetbrains.kotlinx.dataframe.api.*
import java.net.URL

object Data {
    var airports: Map<String, Airport> = emptyMap()

    fun bootstrap() {
        println("Bootstrap...")
        val bootTime = measureTime {
            val airportsUrl = URL("https://davidmegginson.github.io/ourairports-data/airports.csv")
            val frequenciesUrl = URL("https://davidmegginson.github.io/ourairports-data/airport-frequencies.csv")
            val runwaysUrl = URL("https://davidmegginson.github.io/ourairports-data/runways.csv")
            val navaidsUrl = URL("https://davidmegginson.github.io/ourairports-data/navaids.csv")

            val frequencies = loadFrequencies(DataFrame.read(frequenciesUrl))
            println("${frequencies.size} frequencies loaded")

            val navaids = loadNavAids(DataFrame.read(navaidsUrl))
            println("${navaids.size} navigation aids loaded")

            val runways = loadRunways(DataFrame.read(runwaysUrl))
            println("${runways.size} runways loaded")

            airports = loadAirports(
                DataFrame.read(airportsUrl),
                frequencies,
                runways,
                navaids
            )

            println("${airports.size} airports loaded")
        }

        println("Bootstrap completed $bootTime")
    }

    private fun loadAirports(
        frames: AnyFrame,
        frequencies: Map<String, List<Frequency>>,
        runways: Map<String, List<Runway>>,
        navAids: Map<String, List<NavAid>>
    ) = frames.map {
        Airport(
            it["id"] as Int,
            it["ident"] as String,
            it["type"] as String,
            it["name"] as String,
            it["latitude_deg"] as Double,
            it["longitude_deg"] as Double,
            it["elevation_ft"] as Int?,
            it["continent"] as String?,
            it["iso_country"] as String?,
            it["iso_region"] as String?,
            it["municipality"] as String?,
            it["scheduled_service"] as Boolean?,
            it["gps_code"] as String?,
            it["iata_code"] as String?,
            it["local_code"] as String?,
            it["home_link"] as URL?,
            it["wikipedia_link"] as URL?,
            frequencies = frequencies[it["ident"] as String] ?: emptyList(),
            runways = runways[it["ident"] as String] ?: emptyList(),
            navaids = navAids[it["ident"] as String] ?: emptyList(),
        )
    }.associateBy {
        it.ident
    }

    private fun loadFrequencies(frames: AnyFrame): Map<String, List<Frequency>> = frames
        .map {
            Frequency(
                it["id"] as Int,
                it["airport_ref"] as Int,
                it["airport_ident"] as String,
                it["type"] as String,
                it["description"] as String?,
                it["frequency_mhz"] as Double
            )
        }
        .groupBy { it.airport_ident }

    private fun loadRunways(frames: AnyFrame): Map<String, List<Runway>> = frames
        .map {
            Runway(
                it["id"] as Int,
                it["airport_ref"] as Int,
                it["airport_ident"] as String,
                it["length_ft"] as Int?,
                it["width_ft"] as Int?,
                it["surface"] as String?,
                it["lighted"] as Int,
                it["closed"] as Int,
                it["le_ident"] as String?,
                it["le_latitude_deg"] as Double?,
                it["le_longitude_deg"] as Double?,
                it["le_elevation_ft"] as Int?,
                it["le_heading_degT"] as Double?,
                it["le_displaced_threshold_ft"] as Int?,
                it["he_ident"] as String?,
                it["he_latitude_deg"] as Double?,
                it["he_longitude_deg"] as Double?,
                it["he_elevation_ft"] as Int?,
                it["he_heading_degT"] as Double?,
                it["he_displaced_threshold_ft"] as Int?,
                )
        }
        .groupBy { it.airport_ident }

    private fun loadNavAids(frames: AnyFrame): Map<String, List<NavAid>> = frames
        .map {
            NavAid(
                it["id"] as Int,
                it["filename"] as String,
                it["ident"] as String?,
                it["name"] as String,
                it["type"] as String,
                it["frequency_khz"] as Int,
                it["latitude_deg"] as Double,
                it["longitude_deg"] as Double,
                it["elevation_ft"] as Int?,
                it["iso_country"] as String?,
                it["dme_frequency_khz"] as Int?,
                it["dme_channel"] as String?,
                it["dme_latitude_deg"] as Double?,
                it["dme_longitude_deg"] as Double?,
                it["dme_elevation_ft"] as Int?,
                it["slaved_variation_deg"] as Double?,
                it["magnetic_variation_deg"] as Double?,
                it["usageType"] as String?,
                it["power"] as String?,
                it["associated_airport"] as String?,
            )
        }
        .groupBy { it.associated_airport ?: "Unknown" }
}
