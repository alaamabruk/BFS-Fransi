package bfs.digital.fransi.service

import bfs.digital.fransi.api.model.AccountDto
import bfs.digital.fransi.entity.AccountDetails

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
