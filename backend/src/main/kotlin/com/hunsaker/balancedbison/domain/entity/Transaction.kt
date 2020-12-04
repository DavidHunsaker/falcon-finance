package com.hunsaker.balancedbison.domain.entity

import com.hunsaker.balancedbison.domain.enum.TransactionType
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "transaction")
data class Transaction(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val userId: Long,

        val accountId: Long,

        @Enumerated(EnumType.STRING)
        val transactionType: TransactionType,

        // TODO figure out what max size is needed here
        val description: String?,

        val transactionDate: ZonedDateTime? = ZonedDateTime.now(),

        val dateAdded: ZonedDateTime
)
