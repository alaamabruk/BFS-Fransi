package bfs.digital.fransi.service.impl


import bfs.digital.fransi.entity.AccountDetails
import bfs.digital.fransi.entity.TransactionDetails
import bfs.digital.fransi.exceptions.ErrorCode
import bfs.digital.fransi.exceptions.InvalidParameterException
import bfs.digital.fransi.repository.AccountRepository
import bfs.digital.fransi.repository.TransactionRepository
import bfs.digital.fransi.service.AccountService
import bfs.digital.fransi.service.TransferService
import bfs.digital.fransi.api.model.OBWriteTransfer2
import bfs.digital.fransi.enums.OBExternalTransactionType1Code
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class TransferServiceImpl(
    private val accountRepository: AccountRepository,
    private val accountService: AccountService,
    private val transactionRepository: TransactionRepository
) : TransferService {

    companion object {
        private val log = LoggerFactory.getLogger(TransferServiceImpl::class.toString())
    }


    override fun transfer(id: Long?, transfer: OBWriteTransfer2) {
        validateAccountNumbers(id , transfer)
        var fromAccount: AccountDetails? = accountService.getAccountById(id!!)
        if (isInsufficientBalance(fromAccount , transfer)){
            log.error("Insufficient funds")
            throw InvalidParameterException(ErrorCode.INSUFFICIENT_FUNDS)
        }
        var toAccount: AccountDetails? = accountService.getAccountById(transfer.toAccountId)
        val accountTransaction = TransactionDetails()
        accountTransaction.amount = transfer.amount
        log.debug("Transfer Between Accounts:")

        val fromTransaction = TransactionDetails()
        fromTransaction.amount = accountTransaction.amount
        fromTransaction.transactionDate = accountTransaction.transactionDate
        fromTransaction.description = "Transfer to Account (" + toAccount?.accountNumber.toString() + ")"
        fromTransaction.transactionType1Code = OBExternalTransactionType1Code.DEBIT
        debitTransaction(fromAccount, fromTransaction)


        val toTransaction = TransactionDetails()
        toTransaction.amount = accountTransaction.amount
        toTransaction.transactionDate = accountTransaction.transactionDate
        toTransaction.description = "Transfer from Account (" + fromAccount?.accountNumber.toString() + ")"
        toTransaction.transactionType1Code = OBExternalTransactionType1Code.CREDIT
        creditTransaction(toAccount, toTransaction)
        log.info("Transfer Between Accounts: Accounts Updated.")
    }


    private fun validateAccountNumbers(id: Long?, transfer: OBWriteTransfer2) {
        if (transfer.toAccountId  == id) {
            log.error("You can't transfer money to the same account")
            throw InvalidParameterException(ErrorCode.SAME_ACCOUNT_NUMBER)
        }
    }

    private fun isInsufficientBalance(source: AccountDetails?, request: OBWriteTransfer2): Boolean {
        return source?.currentBalance?.compareTo(request.amount)!! < 0
    }


    private fun debitTransaction(account: AccountDetails?, accountTransaction: TransactionDetails) {
        var account = account
        log.info("Debit Transaction from Account:")
        account = accountService.getAccountById(account?.id!!)!!
        val transactionList: MutableList<TransactionDetails>? = account?.accountTransactionList
        var balance: BigDecimal? = account?.currentBalance
        var amount: BigDecimal? = accountTransaction.amount

        log.info(" Convert amount to a negative number since it is a withdraw")
        val negOne = BigDecimal(-1)
        amount = amount?.multiply(negOne)
        balance = balance?.add(amount)
        account?.currentBalance = balance

        log.info("Check if the date was already set, if not set to current date time.")
        if (accountTransaction.transactionDate == null) {
            accountTransaction.transactionDate = Date()
        }
        accountTransaction.runningBalance = balance
        accountTransaction.amount = amount
        accountTransaction.account = account
        transactionList?.add(accountTransaction)
        account?.accountTransactionList = transactionList

        log.info("Debit Transaction success Account: Update Account details")
        accountRepository.save(account)
        log.debug("Debit Transaction from Account: Account Updated.")
    }


    private fun creditTransaction(account: AccountDetails?, accountTransaction: TransactionDetails) {
        var account: AccountDetails? = account
        log.info("Credit Transaction to Account:")
        account = accountService.getAccountById(account?.id!!)!!
        var balance: BigDecimal? = account.currentBalance
        var transactionList: MutableList<TransactionDetails>? = account.accountTransactionList


        if (transactionList != null) {
            log.info(" adding another transaction to the list so calculate the new update")
            balance = balance?.add(accountTransaction.amount)
            account.currentBalance = balance
        }
        log.debug("Credit Transaction to Account: Current Number of Transactions: ->" + transactionList?.size)

        if (accountTransaction.transactionDate == null) {
            accountTransaction.transactionDate = Date()
        }
        accountTransaction.runningBalance = balance
        accountTransaction.account = account
        transactionList?.add(accountTransaction)
        account.accountTransactionList = transactionList

        log.info("Credit Transaction success Account: Update Account details")
        accountRepository.save(account)
        log.debug("Credit Transaction to Account: Account Updated.")
    }


    override fun getLastTwoAccountTransactions(account: AccountDetails?): List<TransactionDetails?>? {
        return transactionRepository.findTop2ByAccountOrderByTransactionDateDesc(account)
    }

}