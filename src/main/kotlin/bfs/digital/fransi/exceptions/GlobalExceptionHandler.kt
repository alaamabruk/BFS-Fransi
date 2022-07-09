package bfs.digital.fransi.exceptions

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    val exceptionLog = "Exception occurred: {}"
    companion object {
        private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.toString())
    }

    @ExceptionHandler(InvalidParameterException::class)
    fun onInvalidParameterException(e: InvalidParameterException): ResponseEntity<ErrorResponse> {
        log.error(exceptionLog, e)
        return ResponseEntity
            .badRequest()
            .body(ErrorResponse(e.errorCode, HttpStatus.BAD_REQUEST,e.errorMessage))
    }


    @ExceptionHandler(Exception::class)
    fun kotlinException(e: Exception): ResponseEntity<ErrorResponse> {
        log.error(exceptionLog, e)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    ErrorCode.INTERNAL_SERVER_ERROR.code, HttpStatus.INTERNAL_SERVER_ERROR,
                    ErrorCode.INTERNAL_SERVER_ERROR.message)
            )
    }

}
