package com.ki960213.kidsandseoul.facility.controller.model

import com.ki960213.kidsandseoul.facility.domain.Area
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArea(
    val leftTop: NetworkPoint,
    val rightTop: NetworkPoint,
    val leftBottom: NetworkPoint,
    val rightBottom: NetworkPoint,
) {

    fun asPointBound() = Area(
        leftTop = leftTop.asPoint(),
        rightTop = rightTop.asPoint(),
        leftBottom = leftBottom.asPoint(),
        rightBottom = rightBottom.asPoint(),
    )
}
