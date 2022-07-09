package bfs.digital.fransi.api.model

import bfs.digital.fransi.enums.OBExternalTransactionType1Code
import java.math.BigDecimal
import java.util.*


class TransactionDto(

    var id: Long? = null,

    var description: String? = null,

    var amount: BigDecimal? = null,

    var runningBalance: BigDecimal? = null,


    var transactionDate: Date? = null,

    var account: AccountDto? = null,

    var transactionType1Code: OBExternalTransactionType1Code? = null


)