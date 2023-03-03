package com.tygrupp.phonebook.contact.controller

data class ApiResponse(
    private val message: String,
    private val status: String,
    private val data: Any?
    )
