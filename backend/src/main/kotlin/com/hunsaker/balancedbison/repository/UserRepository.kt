package com.hunsaker.balancedbison.repository

import com.hunsaker.balancedbison.domain.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Long> {
}