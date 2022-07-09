package bfs.digital.Fransi.service

import bfs.digital.Fransi.entity.AccountDetails
import bfs.digital.Fransi.entity.TransactionDetails
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
