package com.hunsaker.balancedbison.domain.entity

import com.hunsaker.balancedbison.domain.dto.UserDTO
import java.time.ZonedDateTime
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "user")
data class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        val id: Long = 0,

        @Size(max = 256)
        @Column
        val firstName: String,

        @Size(max = 256)
        @Column
        val lastName: String,

        @Size(max = 128)
        @Column
        val email: String,

        @Column
        val createdOn: ZonedDateTime,

        @Column
        val lastModified: ZonedDateTime
)

fun User.toDTO(): UserDTO {
    return UserDTO(
            this.id,
            this.firstName,
            this.lastName,
            this.email,
            this.createdOn,
            this.lastModified
    )
}