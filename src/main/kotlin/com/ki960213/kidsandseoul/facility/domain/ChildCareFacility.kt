package com.ki960213.kidsandseoul.facility.domain

import com.ki960213.kidsandseoul.administrativedong.domain.AdministrativeDong
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class ChildCareFacility(
    id: Long,
    name: String,
    administrativeDong: AdministrativeDong,
    point: Point,
    address: Address,
    detailUrl: String,
    @Enumerated(EnumType.STRING)
    val childCareFacilityType: ChildCareFacilityType,
    @Enumerated(EnumType.STRING)
    val age: Age,
    val isSaturdayOperate: Boolean,
) : Facility(
    id = id,
    name = name,
    administrativeDong = administrativeDong,
    point = point,
    address = address,
    detailUrl = detailUrl,
) {

    override fun isSuitableFor(age: Int): Boolean = age in this.age
}
