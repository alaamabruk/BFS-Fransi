package bfs.digital.Fransi.service


import bfs.digital.Fransi.exceptions.InvalidParameterException
import bfs.digital.Fransi.repository.AccountRepository
import bfs.digital.Fransi.repository.TransactionRepository
import bfs.digital.Fransi.service.impl.TransferServiceImpl
import bfs.digital.Fransi.util.TestUtil.anyObject
import bfs.digital.Fransi.util.TestUtil.getAccountDetails
import bfs.digital.bank.api.model.OBWriteTransfer2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

internal class TransferServiceTests {

    @InjectMocks
    lateinit var transferService: TransferServiceImpl

    @Mock
    lateinit var transactionRepository: TransactionRepository

    @Mock
    lateinit var accountService: AccountService

    @Mock
    lateinit var accountRepository: AccountRepository

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun validateTransferResponse() {

        `when`(accountRepository.save(anyObject())).thenReturn(getAccountDetails())
        `when`(accountService.getAccountById(Mockito.anyLong())).thenReturn(getAccountDetails())
        var request = OBWriteTransfer2(
            toAccountId = 1, amount = BigDecimal(50.0)
        )
        transferService.transfer(2, request)
    }

    @Test
    fun validateTransferResponse_WhenInsufficient_funds() {

        `when`(accountRepository.save(anyObject())).thenReturn(getAccountDetails())
        `when`(accountService.getAccountById(Mockito.anyLong())).thenReturn(getAccountDetails())
        var request = OBWriteTransfer2(
            toAccountId = 1, amount = BigDecimal(100.0)
        )
        Assertions.assertThrows(InvalidParameterException::class.java) {
            transferService.transfer(2, request)
        }

    }

    @Test
    fun validateTransferResponse_WhenFromAccountEqualToAccount() {

        `when`(accountRepository.save(anyObject())).thenReturn(getAccountDetails())
        `when`(accountService.getAccountById(Mockito.anyLong())).thenReturn(getAccountDetails())
        var request = OBWriteTransfer2(
            toAccountId = 1, amount = BigDecimal(40.0)
        )
        Assertions.assertThrows(InvalidParameterException::class.java) {
            transferService.transfer(1, request)
        }

    }


}