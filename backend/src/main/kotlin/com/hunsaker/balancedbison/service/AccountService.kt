package com.hunsaker.balancedbison.service

import com.hunsaker.balancedbison.domain.dto.AccountRequest
import com.hunsaker.balancedbison.domain.entity.Account
import com.hunsaker.balancedbison.domain.enum.TransactionType
import com.hunsaker.balancedbison.repository.AccountRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.ZonedDateTime
import javax.persistence.EntityNotFoundException

private val log = KotlinLogging.logger {}

interface AccountService {
    fun createAccount(accountRequest: AccountRequest): Account
    fun getById(accountId: Long): Account
    fun getAllAccountsForUser(userId: Long): List<Account>
    fun updateBalance(userId: Long, accountId: Long, amount: BigDecimal, transactionType: TransactionType)
}

@Service
class AccountServiceImpl(private val userService: UserService, private val accountRepository: AccountRepository) : AccountService {
    override fun createAccount(accountRequest: AccountRequest): Account {
        val accountToSave = try {
            with(accountRequest) {
                Account(0, userId, name, institution, accountType, balance ?: BigDecimal.ZERO, dateOpened
                        ?: ZonedDateTime.now(), ZonedDateTime.now())
            }
        } catch (e: Exception) {
            // TODO Make this a real exception with better error message
            throw Exception("Oops")
        }

        return accountRepository.save(accountToSave)
    }

    @Transactional
    override fun updateBalance(userId: Long, accountId: Long, amount: BigDecimal, transactionType: TransactionType) {
        log.info { "Updating balance for account $accountId for user $userId" }
        val existingAccount = accountRepository.getOne(accountId)
        val updatedBalance: BigDecimal = when (transactionType) {
            TransactionType.DEBIT -> {
                existingAccount.balance - amount
            }
            TransactionType.CREDIT -> {
                existingAccount.balance + amount
            }
        }
        val updatedAccount = with(existingAccount) { Account(id, this.userId, name, institution, accountType, updatedBalance, dateOpened, ZonedDateTime.now()) }
        accountRepository.saveAndFlush(updatedAccount)
    }

    override fun getById(accountId: Long): Account {
        log.info { "Received request for account $accountId" }
        return accountRepository.findById(accountId).orElseThrow { throw EntityNotFoundException("Could not find an account with id $accountId") }
    }

    override fun getAllAccountsForUser(userId: Long): List<Account> {
        log.info { "Received request to get accounts for user $userId" }
        return accountRepository.findAllByUserId(userId)
    }
}