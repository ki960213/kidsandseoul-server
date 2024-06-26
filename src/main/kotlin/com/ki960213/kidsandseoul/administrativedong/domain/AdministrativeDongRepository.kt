package com.ki960213.kidsandseoul.administrativedong.domain

import org.springframework.data.jpa.repository.JpaRepository

interface AdministrativeDongRepository : JpaRepository<AdministrativeDong, Long> {

    fun findByName(name: String): AdministrativeDong?
}
