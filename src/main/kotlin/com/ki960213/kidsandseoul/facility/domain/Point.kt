package com.ki960213.kidsandseoul.facility.domain

import jakarta.persistence.Embeddable

@Embeddable
data class Point(
    val x: Double,
    val y: Double,
)
