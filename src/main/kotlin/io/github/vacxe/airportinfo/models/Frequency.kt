package io.github.vacxe.airportinfo.models

data class Frequency(
    val id: Int,
    val airport_ref: Int,
    val airport_ident: String,
    val type: String,
    val description: String?,
    val frequency_mhz: Double
)