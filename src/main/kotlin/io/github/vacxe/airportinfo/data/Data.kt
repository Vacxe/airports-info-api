package io.github.vacxe.airportinfo.data

import io.github.vacxe.airportinfo.models.Airport
import io.github.vacxe.airportinfo.models.Frequency
import kotlinx.io.files.FileNotFoundException
import kotlinx.io.files.Path
import org.jetbrains.kotlinx.dataframe.AnyFrame
import java.io.File
import kotlin.time.measureTime
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.io.*
import org.jetbrains.kotlinx.dataframe.api.*
import java.net.URL

object Data {
    var airports: Map<String, Airport> = emptyMap()
    var frequencies: Map<String, List<Frequency>> = emptyMap()

    fun bootstrap(path: Path) {
        println("Bootstrap...")
        val bootTime = measureTime {
            val airportFile = loadFile(path, "airports.csv")
            val airportFrequenciesFile = loadFile(path, "airport-frequencies.csv")
            val runwaysFile = loadFile(path, "runways.csv")
            val navaidsFile = loadFile(path, "navaids.csv")
            val countriesFile = loadFile(path, "countries.csv")
            val airportCommentFile = loadFile(path, "airport-comments.csv")

            println()
            frequencies = loadFrequencies(DataFrame.read(airportFrequenciesFile.absolutePath))
            println("Frequencies loaded")

            airports = loadAirports(
                DataFrame.read(airportFile.absolutePath)
            )
            println("${airports.size} airports loaded")
        }

        println("Bootstrap complete $bootTime")
    }

    private fun loadFile(path: Path, fileName: String): File {
        val file = File(path.toString(), fileName)
        if (file.exists()) {
            println("$fileName ✅")
            return file
        } else {
            println("$fileName ❌")
            throw FileNotFoundException()
        }
    }

    private fun loadAirports(
        frames: AnyFrame
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
            freequencies = frequencies[it["ident"] as String] ?: emptyList()
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
}