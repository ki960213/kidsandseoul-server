package com.ki960213.kidsandseoul.common.extension

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun <T, Long> CrudRepository<T, Long>.findByIdOrThrow(id: Long): T =
    findByIdOrNull(id) ?: throw NoSuchElementException()
