package io.github.vacxe.airportinfo.models

data class NavAid(
    val id: Int,
    val filename: String,
    val ident: String?,
    val name: String,
    val type: String,
    val frequency_khz: Int,
    val latitude_deg: Double,
    val longitude_deg: Double,
    val elevation_ft: Int?,
    val iso_country: String?,
    val dme_frequency_khz: Int?,
    val dme_channel: String?,
    val dme_latitude_deg: Double?,
    val dme_longitude_deg: Double?,
    val dme_elevation_ft: Int?,
    val slaved_variation_deg: Double?,
    val magnetic_variation_deg: Double?,
    val usageType: String?,
    val power: String?,
    val associated_airport: String?,
)
