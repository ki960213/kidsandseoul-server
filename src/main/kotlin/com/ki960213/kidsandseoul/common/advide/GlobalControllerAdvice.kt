package com.ki960213.kidsandseoul.common.advide

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    fun handleNoSuchElementException(e: NoSuchElementException) = Unit

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    fun handleIllegalArgumentException(e: IllegalArgumentException) = Unit

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    fun handleException(e: Exception) = Unit
}
