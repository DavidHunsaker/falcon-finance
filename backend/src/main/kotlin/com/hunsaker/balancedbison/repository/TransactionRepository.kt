package com.hunsaker.balancedbison.repository

import com.hunsaker.balancedbison.domain.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository: JpaRepository<Transaction, Long> {
}