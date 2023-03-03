package com.tygrupp.phonebook.contact.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tygrupp.phonebook.contact.models.Contact
import com.tygrupp.phonebook.contact.models.Entry
import com.tygrupp.phonebook.contact.repository.ContactRepository
import com.tygrupp.phonebook.contact.services.ContactService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ContactController::class])
@SpringBootTest
class ContactControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper) {
    @MockBean private lateinit var contactService : ContactService

}