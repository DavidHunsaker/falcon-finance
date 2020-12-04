package com.hunsaker.balancedbison.domain.dto

import java.time.ZonedDateTime

data class UserDTO(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val email: String,
        val createdOn: ZonedDateTime,
        val lastModified: ZonedDateTime
)