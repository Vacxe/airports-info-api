package io.github.vacxe.airportinfo.tools

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object Distance {
    private const val EARTH_RADIUS_KM = 6_378

    fun distanceBetween(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Double {
        val dLat = Math.toRadians(lat1 - lat2)
        val dLng = Math.toRadians(lng1 - lng2)
        val a = sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat2)) * cos(
            Math.toRadians(lat1)
        ) * sin(dLng / 2) * sin(dLng / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return EARTH_RADIUS_KM * c
    }
}
