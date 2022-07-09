package bfs.digital.Fransi.service.impl


import bfs.digital.Fransi.api.model.AccountDto
import bfs.digital.Fransi.entity.AccountDetails
import bfs.digital.Fransi.exceptions.ErrorCode
import bfs.digital.Fransi.exceptions.InvalidParameterException
import bfs.digital.Fransi.repository.AccountRepository
import bfs.digital.Fransi.service.AccountService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl(private val accountRepository: AccountRepository) : AccountService {

    companion object {
        private val log = LoggerFactory.getLogger(AccountServiceImpl::class.toString())
    }


    override fun createNewAccount(newAccount: AccountDetails): AccountDto? {

        log.info("Set Account Details")
        newAccount.currentBalance = newAccount.openingBalance
        val randomString = UUID.randomUUID().toString().subSequence(0, 15).toString()
        newAccount.accountNumber = randomString

        log.info("Check if Account opening date has been set, if not set it")
        if (newAccount.dateOpened == null) {
            newAccount.dateOpened = Date()
        }

        log.info("Create Account")
        return toAccount(accountRepository.save(newAccount))
    }


    override fun getAccountById(id: Long): AccountDetails? {
        log.info("get account by account id []", id)
        if (id < 0) {
            log.error("get account by account id [] invalid parameters", id)
            throw InvalidParameterException(ErrorCode.VALIDATION_ERROR)
        }
        val account: Optional<AccountDetails?> = accountRepository.findById(id)
        if (!account.isPresent) {
            log.error("get account by account id [] Not found", id)
            throw InvalidParameterException(ErrorCode.ACCT_NOT_FOUND)
        }
        return account.orElse(null)
    }

    override fun getAccount(id: Long): AccountDto? {
        log.info("get account by account id [] ", id)
        return toAccount(getAccountById(id))
    }


    private fun toAccount(
        accountEntity: AccountDetails?
    ) =
        AccountDto(
            id = accountEntity?.id, name = accountEntity?.name, accountNumber = accountEntity?.accountNumber,
            openingBalance = accountEntity?.openingBalance, dateOpened = accountEntity?.dateOpened,
            currentBalance = accountEntity?.currentBalance, accountType = accountEntity?.accountType,
        )


}