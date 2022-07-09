package bfs.digital.fransi.api.model

import bfs.digital.fransi.api.model.enums.OBExternalAccountType1Code
import java.math.BigDecimal
import java.util.*

class AccountDto(
    var id: Long? = null,

    var name: String? = null,

    var accountNumber: String? = null,

    var currentBalance: BigDecimal? = null,

    var openingBalance: BigDecimal? = null,

    var accountType: OBExternalAccountType1Code? = null,

    var dateOpened: Date? = null,

    var accountTransactionList: MutableList<TransactionDto>? = null


)