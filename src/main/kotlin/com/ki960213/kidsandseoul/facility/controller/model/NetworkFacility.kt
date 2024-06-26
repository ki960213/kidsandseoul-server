package com.ki960213.kidsandseoul.facility.controller.model

import com.ki960213.kidsandseoul.facility.domain.ChildCareFacility
import com.ki960213.kidsandseoul.facility.domain.Facility
import com.ki960213.kidsandseoul.facility.domain.KidsCafe
import com.ki960213.kidsandseoul.facility.domain.OtherFacility
import kotlinx.serialization.Serializable

@Serializable
sealed interface NetworkFacility {

    val id: Long

    val name: String

    val point: NetworkPoint

    val address: NetworkAddress

    val detailUrl: String

    val reviewCount: Int

    val starPointAvg: Double

    companion object {

        fun from(facility: Facility): NetworkFacility = when (facility) {
            is ChildCareFacility -> NetworkChildCareFacility(facility)
            is KidsCafe -> NetworkKidsCafe(facility)
            is OtherFacility -> NetworkOtherFacility(facility)
        }
    }
}
