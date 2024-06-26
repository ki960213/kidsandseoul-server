package com.ki960213.kidsandseoul.facility.domain

import java.time.DayOfWeek

sealed interface FacilityFilterConditions {

    val ids: Set<Long>
    val name: String?
    val age: Int?
    val boroughIds: Set<Long>
    val administrativeDongIds: Set<Long>
    val area: Area?
}

data class ChildCareFacilityFilterConditions(
    override val ids: Set<Long> = emptySet(),
    override val name: String?,
    override val age: Int?,
    override val boroughIds: Set<Long>,
    override val administrativeDongIds: Set<Long>,
    override val area: Area? = null,
    val type: ChildCareFacilityType? = null,
    val mustSaturdayOperate: Boolean = false,
) : FacilityFilterConditions

data class KidsCafeFilterConditions(
    override val ids: Set<Long> = emptySet(),
    override val name: String?,
    override val age: Int?,
    override val boroughIds: Set<Long>,
    override val administrativeDongIds: Set<Long>,
    override val area: Area? = null,
    val daysOfWeek: Set<DayOfWeek>,
) : FacilityFilterConditions

data class OtherFacilityFilterConditions(
    override val ids: Set<Long> = emptySet(),
    override val name: String?,
    override val age: Int?,
    override val boroughIds: Set<Long>,
    override val administrativeDongIds: Set<Long>,
    override val area: Area? = null,
    val type: OtherFacilityType? = null,
) : FacilityFilterConditions

data class AllFacilityFilterConditions(
    override val ids: Set<Long> = emptySet(),
    override val name: String?,
    override val age: Int?,
    override val boroughIds: Set<Long>,
    override val administrativeDongIds: Set<Long>,
    override val area: Area? = null,
) : FacilityFilterConditions
