package bfs.digital.Fransi.service

import bfs.digital.Fransi.api.model.AccountDto
import bfs.digital.Fransi.entity.AccountDetails

interface AccountService {

    /**
     * Account Service Interface
     *
     * Expose all functional methods and provide implementations
     *
     */

    fun createNewAccount(account: AccountDetails) : AccountDto?

    fun getAccountById(id: Long) : AccountDetails?

    fun getAccount(id: Long) : AccountDto?

}
