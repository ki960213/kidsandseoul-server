package com.ki960213.kidsandseoul.administrativedong.controller.response

import com.ki960213.kidsandseoul.administrativedong.domain.AdministrativeDong
import com.ki960213.kidsandseoul.administrativedong.domain.Borough

data class AdministrativeDongResponse(
    val id: Long,
    val name: String,
    val borough: BoroughResponse,
) {

    constructor(administrativeDong: AdministrativeDong) : this(
        id = administrativeDong.id,
        name = administrativeDong.name,
        borough = administrativeDong.borough.let(::BoroughResponse)
    )
}

data class BoroughResponse(
    val id: Long,
    val name: String,
) {

    constructor(borough: Borough) : this(
        id = borough.id,
        name = borough.name,
    )
}
