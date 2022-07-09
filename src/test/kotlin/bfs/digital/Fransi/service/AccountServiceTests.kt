package bfs.digital.Fransi.service


import bfs.digital.Fransi.api.model.enums.OBExternalAccountType1Code
import bfs.digital.Fransi.entity.AccountDetails
import bfs.digital.Fransi.exceptions.InvalidParameterException
import bfs.digital.Fransi.repository.AccountRepository
import bfs.digital.Fransi.service.impl.AccountServiceImpl
import bfs.digital.Fransi.util.TestUtil.anyObject
import bfs.digital.Fransi.util.TestUtil.getAccountDetails
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyLong
import org.mockito.MockitoAnnotations
import java.math.BigDecimal
import java.util.*

internal class AccountServiceTests {

    @InjectMocks
    lateinit var accountService: AccountServiceImpl

    @Mock
    lateinit var accountRepository: AccountRepository


    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun validateCreateAccountResponse() {
        `when`(accountRepository.save(anyObject())).thenReturn(getAccountDetails())
        var request = AccountDetails(
            name = "account", currentBalance = BigDecimal(50.0), accountType = OBExternalAccountType1Code.SAVINGS
        )
        val response = accountService.createNewAccount(request)
        Assertions.assertEquals("account", response?.name)
        Assertions.assertEquals(BigDecimal(50.0), response?.currentBalance)
    }


    @Test
    fun validateGetAccountByIdResponse() {
        `when`(accountRepository.findById(anyLong())).thenReturn(Optional.of(getAccountDetails()))

        val response = accountService.getAccount(1)
        Assertions.assertEquals("account", response?.name)
        Assertions.assertEquals(BigDecimal(50.0), response?.currentBalance)
    }


    @Test
    fun validateGetAccountByIdResponse_WhenId_Negative() {
        `when`(accountRepository.findById(anyLong())).thenReturn(Optional.of(getAccountDetails()))
        Assertions.assertThrows(InvalidParameterException::class.java) {
            accountService.getAccount(-1)
        }
    }

}