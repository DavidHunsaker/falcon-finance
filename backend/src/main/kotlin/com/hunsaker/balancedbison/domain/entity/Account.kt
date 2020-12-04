package com.hunsaker.balancedbison.domain.entity

import com.hunsaker.balancedbison.domain.dto.AccountDTO
import com.hunsaker.balancedbison.domain.enum.AccountType
import java.math.BigDecimal
import java.time.ZonedDateTime
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "account")
data class Account(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val userId: Long,

        @Size(max = 256)
        val name: String,

        @Size(max = 256)
        val institution: String,

        val accountType: AccountType,

        val balance: BigDecimal = BigDecimal.ZERO, // Nullable with default of zero

        val dateOpened: ZonedDateTime, // This should probably be nullable and then required for loans and whatnot to display calculations properly

        val lastModified: ZonedDateTime
)

fun Account.toDTO(): AccountDTO {
    return AccountDTO(this.id, this.userId, this.name, this.institution, this.accountType, this.balance, this.dateOpened, this.lastModified)
}
