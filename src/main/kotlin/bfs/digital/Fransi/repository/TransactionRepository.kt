package bfs.digital.Fransi.repository

import bfs.digital.Fransi.entity.AccountDetails
import bfs.digital.Fransi.entity.TransactionDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<TransactionDetails, Long> {
    fun findTop2ByAccountOrderByTransactionDateDesc(account: AccountDetails?): List<TransactionDetails?>?
}