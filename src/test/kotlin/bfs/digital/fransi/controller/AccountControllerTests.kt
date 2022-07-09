package bfs.digital.fransi.controller


import bfs.digital.fransi.api.controller.AccountController
import bfs.digital.fransi.api.model.enums.OBExternalAccountType1Code
import bfs.digital.fransi.exceptions.InvalidParameterException
import bfs.digital.fransi.service.AccountService
import bfs.digital.fransi.util.TestUtil.anyObject
import bfs.digital.fransi.util.TestUtil.getAccountDto
import bfs.digital.bank.api.model.OBWriteAccount1
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

internal class AccountControllerTests {

    @InjectMocks
    lateinit var accountController: AccountController

    @Mock
    lateinit var accountService: AccountService


    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun validateCreateAccountResponse() {
        `when`(accountService.createNewAccount(anyObject())).thenReturn(getAccountDto())
        var request = OBWriteAccount1(
            accountName = "account", openingDeposit = BigDecimal(50.0), accountType = OBExternalAccountType1Code.SAVINGS
        )
        val response = accountController.createAccount(request)
        Assertions.assertEquals(HttpStatus.OK, response?.statusCode)
        Assertions.assertEquals("account", response?.body?.name)
    }


    @Test
    fun validateCreateAccountResponseException() {
        `when`(accountService.createNewAccount(anyObject())).thenReturn(getAccountDto())
        var request = OBWriteAccount1(
            accountName = "account", openingDeposit = BigDecimal(-1), accountType = OBExternalAccountType1Code.SAVINGS
        )

        Assertions.assertThrows(InvalidParameterException::class.java) {
            accountController.createAccount(request)
        }

    }


    @Test
    fun validateGetAccountByIdResponse() {
        `when`(accountService.getAccount(anyLong())).thenReturn(getAccountDto())
        val response = accountController.getAccount(1)
        Assertions.assertEquals(HttpStatus.OK, response?.statusCode)
        Assertions.assertEquals("1234567890", response?.body?.accountNumber)
    }


}