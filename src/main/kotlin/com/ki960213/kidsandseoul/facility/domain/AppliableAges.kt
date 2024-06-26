package com.ki960213.kidsandseoul.facility.domain

import jakarta.persistence.Embeddable

@Embeddable
data class AppliableAges(
    val start: Int,
    val end: Int,
) {

    init {
        require(start <= end) { "시작 나이가 끝 나이보다 적어야 합니다." }
    }

    operator fun contains(age: Int): Boolean = age in start..end
}
