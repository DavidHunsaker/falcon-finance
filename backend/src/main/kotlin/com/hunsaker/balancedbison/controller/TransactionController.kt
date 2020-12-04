package com.hunsaker.balancedbison.controller

import com.hunsaker.balancedbison.domain.dto.TransactionRequest
import com.hunsaker.balancedbison.service.TransactionService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/transactions")
class TransactionController(val transactionService: TransactionService) {

    @PostMapping()
    fun addTransaction(@Valid @RequestBody request: TransactionRequest): ResponseEntity<Any> {

        log.debug { "Received a ${request.transactionType.name} transaction request for user ${request.userId} in account ${request.accountId}" }

        transactionService.addTransaction(request)

        return ResponseEntity.accepted().build()
    }
}