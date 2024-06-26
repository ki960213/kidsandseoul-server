package com.ki960213.kidsandseoul.facility.domain

import com.ki960213.kidsandseoul.administrativedong.domain.AdministrativeDong
import com.ki960213.kidsandseoul.administrativedong.domain.Borough
import com.linecorp.kotlinjdsl.dsl.jpql.Jpql
import com.linecorp.kotlinjdsl.querymodel.jpql.predicate.Predicate
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface FacilityRepository : JpaRepository<Facility, Long>, KotlinJdslJpqlExecutor

fun FacilityRepository.findAll(
    pageable: Pageable,
    conditions: AllFacilityFilterConditions,
): List<Facility> = findAll(pageable) {
    select(
        entity(Facility::class)
    ).from(
        entity(Facility::class)
    ).whereAnd(
        idIsIn<Facility>(conditions.ids),
        nameIsLikeWith<Facility>(conditions.name),
        ageIsSuitable(conditions.age),
        locatedAt<Facility>(conditions.boroughIds, conditions.administrativeDongIds),
        locatedIn<Facility>(conditions.area),
    )
}.filterNotNull()

fun FacilityRepository.countAll(conditions: AllFacilityFilterConditions): Long = findAll {
    select(
        count(entity(Facility::class))
    ).from(
        entity(Facility::class)
    ).whereAnd(
        idIsIn<Facility>(conditions.ids),
        nameIsLikeWith<Facility>(conditions.name),
        ageIsSuitable(conditions.age),
        locatedAt<Facility>(conditions.boroughIds, conditions.administrativeDongIds),
        locatedIn<Facility>(conditions.area),
    )
}.first()!!

private fun Jpql.ageIsSuitable(age: Int?): Predicate? {
    if (age == null) return null
    return or(
        and(
            type(entity(Facility::class)).eq(ChildCareFacility::class),
            value(Age.from(age)).eq(entity(Facility::class).treat(ChildCareFacility::class)(ChildCareFacility::age)),
        ),
        and(
            type(entity(Facility::class)).eq(KidsCafe::class),
            value(age).between(
                entity(Facility::class).treat(KidsCafe::class)(KidsCafe::appliableAges)(AppliableAges::start),
                entity(Facility::class).treat(KidsCafe::class)(KidsCafe::appliableAges)(AppliableAges::end)
            ),
        ),
        and(
            type(entity(Facility::class)).eq(OtherFacility::class),
            value(Age.from(age)).eq(entity(Facility::class).treat(OtherFacility::class)(OtherFacility::age)),
        )
    )
}

fun FacilityRepository.findChildCareFacilities(
    pageable: Pageable,
    conditions: ChildCareFacilityFilterConditions
): List<ChildCareFacility> = findAll(pageable) {
    select(
        entity(ChildCareFacility::class)
    ).from(
        entity(ChildCareFacility::class)
    ).whereAnd(
        idIsIn<ChildCareFacility>(conditions.ids),
        nameIsLikeWith<ChildCareFacility>(conditions.name),
        ageIsSuitableForChildCareFacility(conditions.age),
        locatedAt<ChildCareFacility>(conditions.boroughIds, conditions.administrativeDongIds),
        locatedIn<ChildCareFacility>(conditions.area),
        childCareFacilityTypeIs(conditions.type),
        if (conditions.mustSaturdayOperate) isOpenOnSaturday() else null,
    )
}.filterNotNull()

fun FacilityRepository.countChildCareFacilities(
    conditions: ChildCareFacilityFilterConditions,
): Long = findAll {
    select(
        count(entity(ChildCareFacility::class))
    ).from(
        entity(ChildCareFacility::class)
    ).whereAnd(
        idIsIn<ChildCareFacility>(conditions.ids),
        nameIsLikeWith<ChildCareFacility>(conditions.name),
        ageIsSuitableForChildCareFacility(conditions.age),
        locatedAt<ChildCareFacility>(conditions.boroughIds, conditions.administrativeDongIds),
        locatedIn<ChildCareFacility>(conditions.area),
        childCareFacilityTypeIs(conditions.type),
        if (conditions.mustSaturdayOperate) isOpenOnSaturday() else null,
    )
}.first()!!

private fun Jpql.ageIsSuitableForChildCareFacility(age: Int?): Predicate? = if (age != null) {
    value(Age.from(age)).eq(entity(ChildCareFacility::class)(ChildCareFacility::age))
} else {
    null
}

private fun Jpql.childCareFacilityTypeIs(type: ChildCareFacilityType?): Predicate? =
    if (type != null) path(ChildCareFacility::childCareFacilityType).eq(type) else null

private fun Jpql.isOpenOnSaturday(): Predicate = path(ChildCareFacility::isSaturdayOperate).eq(true)

fun FacilityRepository.findKidsCafes(
    conditions: KidsCafeFilterConditions,
): List<KidsCafe> = findAll(Pageable.unpaged()) {
    select(
        entity(KidsCafe::class)
    ).from(
        entity(KidsCafe::class),
    ).whereAnd(
        idIsIn<KidsCafe>(conditions.ids),
        nameIsLikeWith<KidsCafe>(conditions.name),
        ageIsSuitableForKidsCafe(conditions.age),
        locatedAt<KidsCafe>(conditions.boroughIds, conditions.administrativeDongIds),
        locatedIn<KidsCafe>(conditions.area),
    )
}.filterNotNull()
    .filter { kidsCafe -> kidsCafe.isOpenOn(conditions.daysOfWeek) }

private fun Jpql.ageIsSuitableForKidsCafe(age: Int?): Predicate? = if (age != null) {
    val appliableAges = entity(KidsCafe::class)(KidsCafe::appliableAges)
    value(age).between(appliableAges(AppliableAges::start), appliableAges(AppliableAges::end))
} else {
    null
}

fun FacilityRepository.countKidsCafe(
    conditions: KidsCafeFilterConditions,
): Long = findKidsCafes(conditions = conditions).count().toLong()

fun FacilityRepository.findOtherFacilities(
    pageable: Pageable,
    conditions: OtherFacilityFilterConditions,
): List<OtherFacility> = findAll(pageable) {
    select(
        entity(OtherFacility::class)
    ).from(
        entity(OtherFacility::class)
    ).whereAnd(
        idIsIn<OtherFacility>(conditions.ids),
        nameIsLikeWith<OtherFacility>(conditions.name),
        ageIsSuitableForOtherFacility(conditions.age),
        locatedAt<OtherFacility>(conditions.boroughIds, conditions.administrativeDongIds),
        locatedIn<OtherFacility>(conditions.area),
        otherFacilityTypeIs(conditions.type),
    )
}.filterNotNull()

fun FacilityRepository.countOtherFacilities(
    conditions: OtherFacilityFilterConditions,
): Long = findAll {
    select(
        count(entity(OtherFacility::class))
    ).from(
        entity(OtherFacility::class)
    ).whereAnd(
        idIsIn<OtherFacility>(conditions.ids),
        nameIsLikeWith<OtherFacility>(conditions.name),
        ageIsSuitableForOtherFacility(conditions.age),
        locatedAt<OtherFacility>(conditions.boroughIds, conditions.administrativeDongIds),
        locatedIn<OtherFacility>(conditions.area),
        otherFacilityTypeIs(conditions.type),
    )
}.first()!!

private fun Jpql.ageIsSuitableForOtherFacility(age: Int?): Predicate? = if (age != null) {
    value(Age.from(age)).eq(entity(OtherFacility::class)(OtherFacility::age))
} else {
    null
}

private fun Jpql.otherFacilityTypeIs(type: OtherFacilityType?): Predicate? =
    if (type != null) path(OtherFacility::otherFacilityType).eq(type) else null

private inline fun <reified T : Facility> Jpql.idIsIn(ids: Set<Long>): Predicate? =
    if (ids.isNotEmpty()) entity(T::class)(Facility::id).`in`(ids) else null

private inline fun <reified T : Facility> Jpql.nameIsLikeWith(name: String?): Predicate? =
    if (name?.isNotBlank() == true) entity(T::class)(Facility::name).like("%$name%") else null

private inline fun <reified T : Facility> Jpql.locatedAt(
    boroughIds: Set<Long>,
    administrativeDongIds: Set<Long>
): Predicate? = if (boroughIds.isNotEmpty() || administrativeDongIds.isNotEmpty()) {
    or(
        locatedAtBorough<T>(boroughIds),
        locatedAtAdministrativeDong<T>(administrativeDongIds),
    )
} else {
    null
}

private inline fun <reified T : Facility> Jpql.locatedAtBorough(boroughIds: Set<Long>): Predicate? =
    if (boroughIds.isNotEmpty()) {
        entity(T::class)(Facility::administrativeDong)(AdministrativeDong::borough)(Borough::id).`in`(boroughIds)
    } else {
        null
    }

private inline fun <reified T : Facility> Jpql.locatedAtAdministrativeDong(
    administrativeDongIds: Set<Long>
): Predicate? = if (administrativeDongIds.isNotEmpty()) {
    entity(T::class)(Facility::administrativeDong)(AdministrativeDong::id).`in`(administrativeDongIds)
} else {
    null
}

private inline fun <reified T : Facility> Jpql.locatedIn(
    area: Area?
): Predicate? = if (area != null) {
    and(
        entity(T::class)(Facility::point)(Point::x).between(area.leftTop.x, area.rightTop.x),
        entity(T::class)(Facility::point)(Point::y).between(area.leftBottom.y, area.leftTop.y),
    )
} else {
    null
}

