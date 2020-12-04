package com.hunsaker.balancedbison.domain.dto

import com.hunsaker.balancedbison.domain.enum.AccountType
import java.math.BigDecimal
import java.time.ZonedDateTime

data class AccountDTO(
        val id: Long,
        val userId: Long,
        val name: String,
        val institution: String,
        val accountType: AccountType,
        val balance: BigDecimal,
        val dateOpened: ZonedDateTime?,
        val lastModified: ZonedDateTime
)
