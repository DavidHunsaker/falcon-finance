package com.hunsaker.balancedbison.controller

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger {}

@RestController
class HealthCheckController {

    @GetMapping("/internal/health.check")
    @ResponseStatus(HttpStatus.OK)
    fun check() = "This Bison is Balanced and Healthy"
}