package bfs.digital.fransi.exceptions



class InvalidParameterException(val errorCode: String, val errorMessage: String) : RuntimeException() {

    constructor(errorCode: ErrorCode) : this(errorCode.code, errorCode.message)

}