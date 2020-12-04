package com.hunsaker.balancedbison.service

import com.hunsaker.balancedbison.domain.entity.User
import com.hunsaker.balancedbison.repository.UserRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

interface UserService {
    fun save(user: User): User
    fun findById(id: Long): User
}

@Service
class UserServiceImpl(private val userRepository: UserRepository): UserService {

    override fun save(user: User): User {
        return userRepository.save(user)
    }

    override fun findById(id: Long): User {
        return userRepository.findById(id).orElseThrow { EntityNotFoundException("No user found for id: $id") }
    }
}