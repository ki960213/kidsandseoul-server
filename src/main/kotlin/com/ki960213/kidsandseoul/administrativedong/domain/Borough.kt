package com.ki960213.kidsandseoul.administrativedong.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Borough(
    @Id
    val id: Long,
    @Column
    val name: String,
)
