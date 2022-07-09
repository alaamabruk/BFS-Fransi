package bfs.digital.fransi.entity

import bfs.digital.bank.api.model.enums.OBExternalTransactionType1Code
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "transaction_details")
class TransactionDetails(

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long? = null,

    var description: String? = null,

    var amount: BigDecimal? = null,

    var runningBalance: BigDecimal? = null,

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    var transactionDate: Date? = null,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    var account: AccountDetails? = null,

    var transactionType1Code: OBExternalTransactionType1Code? = null


)