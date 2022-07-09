package bfs.digital.fransi.api.model

import bfs.digital.fransi.enums.OBExternalAccountType1Code
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class OBWriteAccount1(

    @NotNull
    @Size(min = 3, max = 40)
    @Schema(name = "accountName")
    var accountName: String? = null,

    @NotNull
    @Positive
    @Schema(name = "openingDeposit")
    var openingDeposit: BigDecimal? = null,

    @NotEmpty
    @Schema(name = "AccountType")
    var accountType: @Valid OBExternalAccountType1Code? = null,
)