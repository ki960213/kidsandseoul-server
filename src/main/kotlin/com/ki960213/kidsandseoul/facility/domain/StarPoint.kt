package com.ki960213.kidsandseoul.facility.domain

data class StarPoint(val value: Int) {
    init {
        require(value in MIN_STAR_POINT..MAX_STAR_POINT)
    }

    companion object {

        private const val MIN_STAR_POINT = 1
        private const val MAX_STAR_POINT = 5
    }
}
