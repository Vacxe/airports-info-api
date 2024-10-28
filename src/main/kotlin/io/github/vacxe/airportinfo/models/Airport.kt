package io.github.vacxe.airportinfo.models

import java.net.URL

data class Airport(
    val id: Int,
    val ident: String,
    val type: String,
    val name: String,
    val latitude_deg: Double,
    val longitude_deg: Double,
    val elevation_ft: Int?,
    val continent: String?,
    val iso_country: String?,
    val iso_region: String?,
    val municipality: String?,
    val scheduled_service: Boolean?,
    val gps_code: String?,
    val iata_code: String?,
    val local_code: String?,
    val home_link: URL?,
    val wikipedia_link: URL?,
    val frequencies: List<Frequency>,
    val runways: List<Runway>
)
