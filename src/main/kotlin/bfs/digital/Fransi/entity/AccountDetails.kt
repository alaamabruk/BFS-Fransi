package bfs.digital.Fransi.entity

import bfs.digital.Fransi.api.model.enums.OBExternalAccountType1Code
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account_details")
class AccountDetails(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long? = null,

    var name: String? = null,

    var accountNumber: String? = null,

    var currentBalance: BigDecimal? = null,

    var openingBalance: BigDecimal? = null,

    var accountType: OBExternalAccountType1Code? = null,

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    var dateOpened: Date? = null,


    @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @OrderBy("transaction_date DESC")
    @JsonIgnore
    var accountTransactionList: MutableList<TransactionDetails>? = null


)