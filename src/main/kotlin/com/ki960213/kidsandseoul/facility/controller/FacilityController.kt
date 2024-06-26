package com.ki960213.kidsandseoul.facility.controller

import com.ki960213.kidsandseoul.facility.controller.model.NetworkFacility
import com.ki960213.kidsandseoul.facility.controller.model.NetworkFacilityFilterConditions
import com.ki960213.kidsandseoul.facility.domain.StarPoint
import com.ki960213.kidsandseoul.facility.service.FacilityService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RequestMapping("/facilities")
@RestController
class FacilityController(
    private val facilityService: FacilityService,
) {

    @PostMapping
    fun getFacilities(
        pageable: Pageable,
        @RequestBody conditions: NetworkFacilityFilterConditions,
    ): List<NetworkFacility> = facilityService.getFacilities(
        pageable = pageable,
        conditions = conditions.asFacilityFilterConditions(),
    ).map { NetworkFacility.from(it) }

    @PostMapping("/count")
    fun getFacilitiesCount(@RequestBody conditions: NetworkFacilityFilterConditions): Long =
        facilityService.getFacilitiesCount(conditions.asFacilityFilterConditions())

    @GetMapping("/{id}")
    fun getFacility(@PathVariable id: Long): NetworkFacility =
        facilityService.getFacility(id).let { NetworkFacility.from(it) }

    @PatchMapping("/{id}/addReview")
    fun addReviewOfStarPoint(
        @PathVariable id: Long,
        @RequestParam starPoint: Int
    ) {
        facilityService.addReviewOf(id, StarPoint(starPoint))
    }

    @PatchMapping("/{id}/deleteReview")
    fun deleteReviewOfStarPoint(
        @PathVariable id: Long,
        @RequestParam starPoint: Int
    ) {
        facilityService.deleteReviewOf(id, StarPoint(starPoint))
    }
}
