package com.ki960213.kidsandseoul.facility.controller.model

import com.ki960213.kidsandseoul.facility.domain.ChildCareFacility
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("NetworkChildCareFacility")
data class NetworkChildCareFacility(
    override val id: Long,
    override val name: String,
    val childCareFacilityType: NetworkChildCareFacilityType,
    override val point: NetworkPoint,
    override val address: NetworkAddress,
    override val detailUrl: String,
    override val reviewCount: Int,
    override val starPointAvg: Double,
    val isSaturdayOperate: Boolean,
) : NetworkFacility {

    constructor(facility: ChildCareFacility) : this(
        id = facility.id,
        name = facility.name,
        childCareFacilityType = NetworkChildCareFacilityType.from(facility.childCareFacilityType),
        point = NetworkPoint(facility.point),
        address = NetworkAddress(facility.address),
        detailUrl = facility.detailUrl,
        reviewCount = facility.reviewCount,
        starPointAvg = facility.starPointAvg,
        isSaturdayOperate = facility.isSaturdayOperate,
    )
}
