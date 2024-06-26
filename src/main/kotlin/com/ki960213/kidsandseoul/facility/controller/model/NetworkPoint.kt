package com.ki960213.kidsandseoul.facility.controller.model

import com.ki960213.kidsandseoul.facility.domain.Point
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPoint(
    val x: Double,
    val y: Double,
) {

    constructor(point: Point) : this(point.x, point.y)

    fun asPoint() = Point(x, y)
}
