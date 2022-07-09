package bfs.digital.fransi.service

import bfs.digital.fransi.entity.AccountDetails
import bfs.digital.fransi.entity.TransactionDetails
import bfs.digital.fransi.api.model.OBWriteTransfer2

interface TransferService {

    /**
     * Transfer Service Interface
     *
     * Expose all functional methods and provide implementations
     *
     */


    fun transfer(id: Long?, transfer: OBWriteTransfer2)

    fun getLastTwoAccountTransactions(account: AccountDetails?): List<TransactionDetails?>?


}
