package io.github.vacxe.airportinfo.models

data class Runway(
    val id: Int,
    val airport_ref: Int,
    val airport_ident: String,
    val length_ft: Int?,
    val width_ft: Int?,
    val surface: String?,
    val lighted: Int,
    val closed: Int,
    val le_ident: String?,
    val le_latitude_deg: Double?,
    val le_longitude_deg: Double?,
    val le_elevation_ft: Int?,
    val le_heading_degT: Double?,
    val le_displaced_threshold_ft: Int?,
    val he_ident: String?,
    val he_latitude_deg: Double?,
    val he_longitude_deg: Double?,
    val he_elevation_ft: Int?,
    val he_heading_degT: Double?,
    val he_displaced_threshold_ft: Int?,
)
