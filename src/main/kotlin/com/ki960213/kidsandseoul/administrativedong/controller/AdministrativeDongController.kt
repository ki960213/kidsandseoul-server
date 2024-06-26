package com.ki960213.kidsandseoul.administrativedong.controller

import com.ki960213.kidsandseoul.administrativedong.controller.response.AdministrativeDongResponse
import com.ki960213.kidsandseoul.administrativedong.domain.AdministrativeDongRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("administrative-dongs")
@RestController
class AdministrativeDongController(
    private val administrativeDongRepository: AdministrativeDongRepository,
) {

    @GetMapping
    fun getAdministrativeDongs(): List<AdministrativeDongResponse> =
        administrativeDongRepository.findAll().map(::AdministrativeDongResponse)
}
