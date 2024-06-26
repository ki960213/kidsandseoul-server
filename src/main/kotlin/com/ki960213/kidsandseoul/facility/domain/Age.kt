package com.ki960213.kidsandseoul.facility.domain

enum class Age(private val start: Int, private val end: Int) {
    INFANT(1, 5),
    KINDERGARTNER(5, 7),
    ELEMENTARY_STUDENT(8, 13),
    MIDDLE_STUDENT(14, 16),
    ELEMENTARY_AND_MIDDLE_STUDENT(8, 16);

    operator fun contains(age: Int): Boolean = age in start..end

    companion object {

        fun from(age: Int): Age? = entries.find { age in it }
    }
}
