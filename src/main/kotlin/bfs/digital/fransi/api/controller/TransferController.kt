package bfs.digital.fransi.api.controller

import bfs.digital.fransi.entity.AccountDetails
import bfs.digital.fransi.entity.TransactionDetails
import bfs.digital.fransi.service.AccountService
import bfs.digital.fransi.service.TransferService
import bfs.digital.bank.api.model.OBWriteTransfer2
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/protected/v1")
@Validated
class TransferController(
    private val transferService: TransferService,
    private val accountService: AccountService
) {


    companion object {
        private val log = LoggerFactory.getLogger(TransferController::class.toString())
    }

    @ApiResponses(
        value = [ApiResponse(code = 201, message = "Ok", response = TransactionDetails::class), ApiResponse(
            code = 400,
            message = "Required field is missing."
        )]
    )
    @PostMapping("/account/{id}/transfer")
    fun transferFunds(
        @PathVariable("id") id: Long,
        @RequestBody transfer: @Valid OBWriteTransfer2
    ): ResponseEntity<List<TransactionDetails?>?>? {
        val fromAccount: AccountDetails? = accountService.getAccountById(id)
        val toAccount: AccountDetails? = accountService.getAccountById(transfer.toAccountId)
        val transaction = TransactionDetails()
        transaction.amount = transfer.amount

        transferService.transfer(id, transfer)
        log.info(
            "Funds of amount: " + transfer.amount.toString() + " transferred from account :" +
                    fromAccount?.accountNumber.toString() + " to account: " + toAccount?.accountNumber
        )
        return ResponseEntity.ok(transferService.getLastTwoAccountTransactions(fromAccount))
    }


}

