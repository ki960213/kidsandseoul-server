package com.ki960213.kidsandseoul.facility.domain

import com.ki960213.kidsandseoul.administrativedong.domain.AdministrativeDong
import jakarta.persistence.*

@Entity
@Inheritance
sealed class Facility(
    @Id @GeneratedValue
    val id: Long = -1,

    val name: String,

    @ManyToOne
    @JoinColumn(name = "administrativeDongId")
    val administrativeDong: AdministrativeDong,

    @Embedded
    val point: Point,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "base", column = Column(name = "base_address")),
        AttributeOverride(name = "detail", column = Column(name = "detail_address")),
    )
    val address: Address,

    @Column(length = 1000)
    val detailUrl: String,

    reviewCount: Int = 0,

    starPointAvg: Double = 0.0,
) {

    var reviewCount: Int = reviewCount
        private set

    var starPointAvg: Double = starPointAvg
        private set

    abstract fun isSuitableFor(age: Int): Boolean

    fun addReviewOf(starPoint: StarPoint) {
        val newReviewCount = reviewCount + 1
        starPointAvg = (starPointAvg * reviewCount + starPoint.value) / newReviewCount
        reviewCount = newReviewCount
    }

    fun deleteReviewOf(starPoint: StarPoint) {
        if (reviewCount == 0) return
        if (reviewCount == 1) {
            reviewCount = 0
            starPointAvg = 0.0
            return
        }
        val newReviewCount = reviewCount - 1
        starPointAvg = (starPointAvg * reviewCount - starPoint.value) / newReviewCount
        reviewCount = newReviewCount
    }
}
