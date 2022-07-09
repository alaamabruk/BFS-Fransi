package bfs.digital.Fransi.api.controller

import bfs.digital.Fransi.api.model.AccountDto
import bfs.digital.Fransi.entity.AccountDetails
import bfs.digital.Fransi.exceptions.ErrorCode
import bfs.digital.Fransi.exceptions.InvalidParameterException
import bfs.digital.Fransi.service.AccountService
import bfs.digital.Fransi.util.ACCT_MIN_BALANCE
import bfs.digital.bank.api.model.OBWriteAccount1
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import javax.validation.Valid


@RestController
@RequestMapping("/protected/v1")
@Validated
class AccountController(private val accountService: AccountService) {


    companion object {
        private val log = LoggerFactory.getLogger(AccountController::class.toString())
    }

    @ApiOperation(value = "create new API")
    @ApiResponses(
        value = [ApiResponse(
            code = 200, message = "Ok", response = AccountDto::class
        ),
            ApiResponse(code = 400, message = "Provided request is invalid"), ApiResponse(
                code = 404,
                message = "Account not found"
            )]
    )
    @PostMapping("/account")
    fun createAccount(@RequestBody account: @Valid OBWriteAccount1?): ResponseEntity<AccountDto>? {
        log.info("[{}] Create accounts API hit")
        log.debug("[{}]createAccount with account [{}]", account)
        val newAccount = AccountDetails()


        if (account?.openingDeposit?.compareTo(BigDecimal(ACCT_MIN_BALANCE))!! >= 0) {
            newAccount.name = account.accountName
            newAccount.openingBalance = account.openingDeposit
            newAccount.accountType = account.accountType
        } else {
            throw InvalidParameterException(ErrorCode.NOT_ACCEPTED)
        }
        log.info("account created with details: $newAccount")
        return ResponseEntity.ok(accountService.createNewAccount(newAccount))
    }


    @ApiResponses(
        value = [ApiResponse(
            code = 200, message = "Ok", response = AccountDto::class
        ),
            ApiResponse(code = 400, message = "Provided request is invalid"), ApiResponse(
                code = 404,
                message = "Account not found"
            )]
    )
    @GetMapping("/account/{id}")
    fun getAccount(@PathVariable("id") id: Long): ResponseEntity<AccountDto>? {
        log.info("[{}] get account by id API hit")
        val account: AccountDto? = accountService.getAccount(id)
        return ResponseEntity.ok(account)
    }

}

