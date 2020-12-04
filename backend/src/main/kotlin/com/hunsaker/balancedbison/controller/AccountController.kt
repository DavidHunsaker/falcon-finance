package com.hunsaker.balancedbison.controller

import com.hunsaker.balancedbison.domain.dto.AccountDTO
import com.hunsaker.balancedbison.domain.dto.AccountRequest
import com.hunsaker.balancedbison.domain.entity.toDTO
import com.hunsaker.balancedbison.service.AccountService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/account")
class AccountController(val accountService: AccountService) {

    @PostMapping("/create")
    fun createAccount(@Valid @RequestBody accountRequest: AccountRequest): ResponseEntity<AccountDTO> {
        val persistedAccount = accountService.createAccount(accountRequest)
        return ResponseEntity.ok(persistedAccount.toDTO())
    }

    @GetMapping("/{id}")
    fun getAccount(@PathVariable id: Long): ResponseEntity<AccountDTO> {
        val persistedAccount = accountService.getById(id)
        return ResponseEntity.ok(persistedAccount.toDTO())
    }

    @GetMapping("/user/{id}")
    fun getAccounts(@PathVariable id: Long): ResponseEntity<List<AccountDTO>> {
        val userAccounts = accountService.getAllAccountsForUser(id)
        return ResponseEntity.ok(userAccounts.map { it.toDTO() } )
    }
}