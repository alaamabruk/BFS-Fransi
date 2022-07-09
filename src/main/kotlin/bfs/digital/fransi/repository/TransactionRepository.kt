package bfs.digital.fransi.repository

import bfs.digital.fransi.entity.AccountDetails
import bfs.digital.fransi.entity.TransactionDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<TransactionDetails, Long> {
    fun findTop2ByAccountOrderByTransactionDateDesc(account: AccountDetails?): List<TransactionDetails?>?
}