package com.hunsaker.balancedbison.domain.dto

import com.hunsaker.balancedbison.domain.enum.TransactionType
import java.math.BigDecimal
import java.time.ZonedDateTime

data class TransactionRequest(
        val userId: Long,
        val accountId: Long,
        val transactionType: TransactionType,
        val amount: BigDecimal,
        val transactionDate: ZonedDateTime?,
        val description: String?
)