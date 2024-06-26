package com.ki960213.kidsandseoul.facility.controller.model

import com.ki960213.kidsandseoul.facility.domain.OtherFacility
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("NetworkOtherFacility")
data class NetworkOtherFacility(
    override val id: Long,
    override val name: String,
    val otherFacilityType: NetworkOtherFacilityType,
    override val point: NetworkPoint,
    override val address: NetworkAddress,
    override val detailUrl: String,
    override val reviewCount: Int,
    override val starPointAvg: Double,
) : NetworkFacility {

    constructor(facility: OtherFacility) : this(
        id = facility.id,
        name = facility.name,
        otherFacilityType = NetworkOtherFacilityType.from(facility.otherFacilityType),
        point = NetworkPoint(facility.point),
        address = NetworkAddress(facility.address),
        detailUrl = facility.detailUrl,
        reviewCount = facility.reviewCount,
        starPointAvg = facility.starPointAvg,
    )
}
