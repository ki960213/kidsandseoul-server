package com.ki960213.kidsandseoul.administrativedong.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class AdministrativeDong(
    @Id
    val id: Long,
    val name: String,
    @ManyToOne
    @JoinColumn(name = "boroughId")
    val borough: Borough,
)
