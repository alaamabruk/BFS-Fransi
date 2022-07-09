package bfs.digital.fransi.service

import bfs.digital.fransi.entity.AccountDetails
import bfs.digital.fransi.entity.TransactionDetails
import bfs.digital.bank.api.model.OBWriteTransfer2

interface TransferService {

    /**
     * Account Service Interface
     *
     * Expose all functional methods and provide implementations
     *
     */


    fun transfer(id: Long?, transfer: OBWriteTransfer2)

    fun getLastTwoAccountTransactions(account: AccountDetails?): List<TransactionDetails?>?


}
