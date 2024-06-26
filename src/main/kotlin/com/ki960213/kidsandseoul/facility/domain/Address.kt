package com.ki960213.kidsandseoul.facility.domain

import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    val base: String,
    val detail: String,
    val zipCode: String,
)
