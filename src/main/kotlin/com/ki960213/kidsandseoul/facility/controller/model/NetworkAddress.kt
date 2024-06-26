package com.ki960213.kidsandseoul.facility.controller.model

import com.ki960213.kidsandseoul.facility.domain.Address
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAddress(
    val zipCode: String,
    val base: String,
    val detail: String,
) {

    constructor(address: Address) : this(
        zipCode = address.zipCode,
        base = address.base,
        detail = address.detail
    )
}
