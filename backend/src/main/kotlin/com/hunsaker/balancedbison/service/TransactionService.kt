package com.hunsaker.balancedbison.service

import com.hunsaker.balancedbison.domain.dto.TransactionRequest
import com.hunsaker.balancedbison.domain.entity.Transaction
import com.hunsaker.balancedbison.repository.TransactionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

interface TransactionService {
    fun addTransaction(transactionRequest: TransactionRequest)
}

@Service
class TransactionServiceImpl(
        val transactionRepository: TransactionRepository,
        val accountService: AccountService
): TransactionService {

    @Transactional
    override fun addTransaction(transactionRequest: TransactionRequest) {
        val transaction =
                with(transactionRequest) {
                    Transaction(0, userId, accountId, transactionType, description, transactionDate, ZonedDateTime.now())
                }
        transactionRepository.save(transaction)
        accountService.updateBalance(transactionRequest.userId, transactionRequest.accountId, transactionRequest.amount, transactionRequest.transactionType)
    }
}