package com.hunsaker.balancedbison.repository

import com.hunsaker.balancedbison.domain.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<Account, Long> {
    fun findAllByUserId(userId: Long): List<Account>
}