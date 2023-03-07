package com.tygrupp.phonebook.contact.services

import com.tygrupp.phonebook.contact.models.Contact
import com.tygrupp.phonebook.contact.models.Entry
import org.springframework.stereotype.Service
import java.util.Optional

@Service
interface ContactService {

    fun createContact(entry: Entry): Contact
    fun findContactByPhoneNumber(phoneNumber: String): Optional<Contact>?
    fun findContactByName(name: String): List<Contact>?
    fun findAllContact(): List<Contact>?
    fun deleteContact(phoneNumber: String)
}