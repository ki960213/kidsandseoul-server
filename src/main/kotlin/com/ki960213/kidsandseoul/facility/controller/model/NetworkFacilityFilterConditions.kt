package com.ki960213.kidsandseoul.facility.controller.model

import com.ki960213.kidsandseoul.facility.domain.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.DayOfWeek

@Serializable
sealed interface NetworkFacilityFilterConditions {

    val ids: Set<Long>
    val name: String?
    val age: Int?
    val boroughIds: Set<Long>
    val administrativeDongIds: Set<Long>
    val area: NetworkArea?
    fun asFacilityFilterConditions(): FacilityFilterConditions
}

@Serializable
@SerialName("NetworkChildCareFilterConditions")
data class NetworkChildCareFilterConditions(
    override val ids: Set<Long> = emptySet(),
    override val name: String? = null,
    override val age: Int? = null,
    override val boroughIds: Set<Long> = emptySet(),
    override val administrativeDongIds: Set<Long> = emptySet(),
    override val area: NetworkArea? = null,
    val childCareFacilityType: NetworkChildCareFacilityType? = null,
    val mustSaturdayOperate: Boolean = false,
) : NetworkFacilityFilterConditions {

    override fun asFacilityFilterConditions(): FacilityFilterConditions = ChildCareFacilityFilterConditions(
        ids = ids,
        name = name,
        age = age,
        boroughIds = boroughIds,
        administrativeDongIds = administrativeDongIds,
        area = area?.asPointBound(),
        type = childCareFacilityType?.asChildCareFacilityType(),
        mustSaturdayOperate = mustSaturdayOperate,
    )
}

@Serializable
@SerialName("NetworkKidsCafeFilterConditions")
data class NetworkKidsCafeFilterConditions(
    override val ids: Set<Long> = emptySet(),
    override val name: String? = null,
    override val age: Int? = null,
    override val boroughIds: Set<Long> = emptySet(),
    override val administrativeDongIds: Set<Long> = emptySet(),
    override val area: NetworkArea? = null,
    val daysOfWeek: Set<String> = emptySet(),
) : NetworkFacilityFilterConditions {

    override fun asFacilityFilterConditions(): FacilityFilterConditions = KidsCafeFilterConditions(
        ids = ids,
        name = name,
        age = age,
        boroughIds = boroughIds,
        administrativeDongIds = administrativeDongIds,
        area = area?.asPointBound(),
        daysOfWeek = daysOfWeek.map(DayOfWeek::valueOf).toSet(),
    )
}

@Serializable
@SerialName("NetworkOtherFilterConditions")
data class NetworkOtherFilterConditions(
    override val ids: Set<Long> = emptySet(),
    override val name: String? = null,
    override val age: Int? = null,
    override val boroughIds: Set<Long> = emptySet(),
    override val administrativeDongIds: Set<Long> = emptySet(),
    override val area: NetworkArea? = null,
    val otherFacilityType: NetworkOtherFacilityType? = null,
) : NetworkFacilityFilterConditions {

    override fun asFacilityFilterConditions(): FacilityFilterConditions = OtherFacilityFilterConditions(
        ids = ids,
        name = name,
        age = age,
        boroughIds = boroughIds,
        administrativeDongIds = administrativeDongIds,
        area = area?.asPointBound(),
        type = otherFacilityType?.asOtherFacilityType(),
    )
}

@Serializable
@SerialName("NetworkAllFilterConditions")
data class NetworkAllFilterConditions(
    override val ids: Set<Long> = emptySet(),
    override val name: String? = null,
    override val age: Int? = null,
    override val boroughIds: Set<Long> = emptySet(),
    override val administrativeDongIds: Set<Long> = emptySet(),
    override val area: NetworkArea? = null,
) : NetworkFacilityFilterConditions {

    override fun asFacilityFilterConditions(): FacilityFilterConditions = AllFacilityFilterConditions(
        ids = ids,
        name = name,
        age = age,
        boroughIds = boroughIds,
        administrativeDongIds = administrativeDongIds,
        area = area?.asPointBound(),
    )
}
