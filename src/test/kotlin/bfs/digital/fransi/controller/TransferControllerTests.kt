package bfs.digital.fransi.controller


import bfs.digital.fransi.api.controller.TransferController
import bfs.digital.fransi.service.AccountService
import bfs.digital.fransi.service.impl.TransferServiceImpl
import bfs.digital.fransi.util.TestUtil.anyObject
import bfs.digital.fransi.util.TestUtil.getAccountDetails
import bfs.digital.fransi.util.TestUtil.getTransactionDetails
import bfs.digital.bank.api.model.OBWriteTransfer2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyLong
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import java.math.BigDecimal

internal class TransferControllerTests {

    @InjectMocks
    lateinit var transferController: TransferController

    @Mock
    lateinit var transferService: TransferServiceImpl

    @Mock
    lateinit var accountService: AccountService


    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun validateTransferResponse() {
        `when`(transferService.getLastTwoAccountTransactions(anyObject())).thenReturn(listOf(getTransactionDetails()))
        `when`(accountService.getAccountById(anyLong())).thenReturn(getAccountDetails())

        var request = OBWriteTransfer2(
            toAccountId = 1, amount = BigDecimal(50.0)
        )
        val response = transferController.transferFunds(2, request)
        Assertions.assertEquals(HttpStatus.OK, response?.statusCode)
        Assertions.assertEquals("account transfer", response?.body?.get(0)?.description)
    }


}