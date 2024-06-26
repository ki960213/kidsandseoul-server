package com.ki960213.kidsandseoul.facility.controller.model

import com.ki960213.kidsandseoul.facility.domain.KidsCafe
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.DayOfWeek

@Serializable
@SerialName("NetworkKidsCafe")
data class NetworkKidsCafe(
    override val id: Long,
    override val name: String,
    override val point: NetworkPoint,
    override val address: NetworkAddress,
    override val detailUrl: String,
    override val reviewCount: Int,
    override val starPointAvg: Double,
    val contact: String,
    val operatingDays: List<String>,    // DayOfWeek
    val appliableAges: List<Int>,
) : NetworkFacility {

    constructor(kidsCafe: KidsCafe) : this(
        id = kidsCafe.id,
        name = kidsCafe.name,
        point = NetworkPoint(kidsCafe.point),
        address = NetworkAddress(kidsCafe.address),
        detailUrl = kidsCafe.detailUrl,
        reviewCount = kidsCafe.reviewCount,
        starPointAvg = kidsCafe.starPointAvg,
        contact = kidsCafe.contact,
        operatingDays = kidsCafe.operatingDays.map(DayOfWeek::toString),
        appliableAges = kidsCafe.appliableAges.let { it.start..it.end }.toList(),
    )
}
