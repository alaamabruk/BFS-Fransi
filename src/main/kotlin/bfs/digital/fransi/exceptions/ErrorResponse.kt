package bfs.digital.fransi.exceptions

import org.springframework.http.HttpStatus
import java.util.Collections.emptyMap

data class ErrorResponse(val code: String?, val httpStatus: HttpStatus?, val message: String?,
                         val errors: Map<String, String?> = emptyMap())