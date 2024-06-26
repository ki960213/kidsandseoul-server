package com.ki960213.kidsandseoul.facility.domain

import com.ki960213.kidsandseoul.administrativedong.domain.AdministrativeDong
import jakarta.persistence.*
import java.time.DayOfWeek

@Entity
class KidsCafe(
    id: Long,
    name: String,
    administrativeDong: AdministrativeDong,
    point: Point,
    address: Address,
    detailUrl: String,
    val contact: String,
    @ElementCollection
    @Enumerated(EnumType.STRING)
    val operatingDays: Set<DayOfWeek>,
    @Embedded
    val appliableAges: AppliableAges,
) : Facility(
    id = id,
    name = name,
    administrativeDong = administrativeDong,
    point = point,
    address = address,
    detailUrl = detailUrl,
) {

    override fun isSuitableFor(age: Int): Boolean = age in appliableAges

    fun isOpenOn(daysOfWeek: Set<DayOfWeek>): Boolean = daysOfWeek.all { it in operatingDays }
}
