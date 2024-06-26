package com.ki960213.kidsandseoul.administrativedong.domain

import org.springframework.data.jpa.repository.JpaRepository

interface BoroughRepository : JpaRepository<Borough, Long> {

    fun findByName(name: String): Borough?
}
