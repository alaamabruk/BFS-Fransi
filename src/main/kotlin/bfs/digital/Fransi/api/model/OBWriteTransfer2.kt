package bfs.digital.bank.api.model

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class OBWriteTransfer2(


    @NotNull
    @Schema(name = "toAccountId")
    var toAccountId: Long ,

    @Positive
    @NotNull
    @Schema(name = "amount")
    val amount: BigDecimal? = null

)