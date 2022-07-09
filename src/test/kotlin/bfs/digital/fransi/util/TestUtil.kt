package bfs.digital.fransi.util

import bfs.digital.fransi.api.model.AccountDto
import bfs.digital.fransi.enums.OBExternalAccountType1Code
import bfs.digital.fransi.entity.AccountDetails
import bfs.digital.fransi.entity.TransactionDetails
import org.mockito.Mockito
import java.math.BigDecimal

object TestUtil {
    fun <T> anyObject(): T {
        return Mockito.any()
    }

    fun getAccountDto() =
        AccountDto(
            id = 1, name = "account", accountNumber = "1234567890", accountType = OBExternalAccountType1Code.SAVINGS,
            openingBalance = BigDecimal.valueOf(12), currentBalance = BigDecimal.valueOf(12)
        )


    fun getAccountDetails() =
        AccountDetails(
            id = 1, name = "account", accountNumber = "1234567890", accountType = OBExternalAccountType1Code.SAVINGS,
            openingBalance = BigDecimal.valueOf(50), currentBalance = BigDecimal.valueOf(50)
        )

    fun getTransactionDetails() =
        TransactionDetails(
            id = 1,
            description = "account transfer",
            amount = BigDecimal.valueOf(50),
            runningBalance = BigDecimal.valueOf(50),
        )


}