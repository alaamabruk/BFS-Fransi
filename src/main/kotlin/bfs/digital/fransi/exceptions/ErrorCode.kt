package bfs.digital.fransi.exceptions

import bfs.digital.fransi.util.ACCT_MIN_BALANCE

enum class ErrorCode(val code: String, val message: String) {
    VALIDATION_ERROR("VALIDATION_ERROR", "Error with input parameters."),
    ACCT_NOT_FOUND("ACCT_NOT_FOUND_ERROR", "account Not Found. Unable to locate account with id"),
    INSUFFICIENT_FUNDS("INSUFFICIENT_FUNDS", "Insufficient funds"),
    SAME_ACCOUNT_NUMBER("SAME_ACCOUNT_NUMBER", " You can't transfer money to the same account"),
    NOT_ACCEPTED("NOT_ACCEPTED", "The initial deposit entered does not meet the minimum amount " +
            "$ACCT_MIN_BALANCE required. Please enter a valid deposit amount."),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal server error.")
}