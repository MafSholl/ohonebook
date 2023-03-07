package com.tygrupp.phonebook.contact.exception.Handler

import com.tygrupp.phonebook.contact.controller.ApiResponse
import com.tygrupp.phonebook.contact.exception.exceptions.PhoneBookException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler
    fun conflictHandler(ex: PhoneBookException) : ResponseEntity<*> {
        val body = ApiResponse("Failure", ex.message, ex.cause)
        return ResponseEntity.badRequest().body(body)
    }
}