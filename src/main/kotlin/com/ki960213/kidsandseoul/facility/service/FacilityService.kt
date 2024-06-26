package com.ki960213.kidsandseoul.facility.service

import com.ki960213.kidsandseoul.common.extension.findByIdOrThrow
import com.ki960213.kidsandseoul.facility.domain.*
import jakarta.transaction.Transactional
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Transactional
@Service
class FacilityService(
    private val facilityRepository: FacilityRepository,
) {

    fun getFacilities(
        pageable: Pageable,
        conditions: FacilityFilterConditions,
    ): List<Facility> = when (conditions) {
        is AllFacilityFilterConditions -> facilityRepository.findAll(pageable, conditions)
        is ChildCareFacilityFilterConditions -> facilityRepository.findChildCareFacilities(pageable, conditions)
        is KidsCafeFilterConditions -> if (pageable.pageNumber >= 1) {
            emptyList()
        } else {
            facilityRepository.findKidsCafes(conditions)
        }

        is OtherFacilityFilterConditions -> facilityRepository.findOtherFacilities(pageable, conditions)
    }

    fun getFacilitiesCount(conditions: FacilityFilterConditions): Long = when (conditions) {
        is AllFacilityFilterConditions -> facilityRepository.countAll(conditions)
        is ChildCareFacilityFilterConditions -> facilityRepository.countChildCareFacilities(conditions)
        is KidsCafeFilterConditions -> facilityRepository.countKidsCafe(conditions)
        is OtherFacilityFilterConditions -> facilityRepository.countOtherFacilities(conditions)
    }

    fun getFacility(id: Long): Facility = facilityRepository.findByIdOrThrow(id)

    fun addReviewOf(facilityId: Long, starPoint: StarPoint) {
        val facility = facilityRepository.findByIdOrNull(facilityId) ?: return
        facility.addReviewOf(starPoint)
    }

    fun deleteReviewOf(facilityId: Long, starPoint: StarPoint) {
        val facility = facilityRepository.findByIdOrNull(facilityId) ?: return
        facility.deleteReviewOf(starPoint)
    }
}
