package com.hunsaker.balancedbison.controller

import com.hunsaker.balancedbison.domain.dto.NewUserRequest
import com.hunsaker.balancedbison.domain.dto.UserDTO
import com.hunsaker.balancedbison.domain.entity.User
import com.hunsaker.balancedbison.domain.entity.toDTO
import com.hunsaker.balancedbison.service.UserService
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime
import javax.validation.Valid

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: Long): ResponseEntity<UserDTO> {
        log.info { "Received request to find user by id - $id" }
        val user = userService.findById(id)
        return ResponseEntity.ok(user.toDTO())
    }

    @PostMapping("/create")
    fun createUser(
            @Valid @RequestBody request: NewUserRequest
    ): ResponseEntity<UserDTO> {
        log.debug { "Received request to save user for ${request.firstName} ${request.lastName} with email ${request.email}" }
        val user: User = User(0, request.firstName, request.lastName, request.email, ZonedDateTime.now(), ZonedDateTime.now())

        val saved = userService.save(user)

        return ResponseEntity.ok(saved.toDTO())
    }
}