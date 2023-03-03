package com.tygrupp.phonebook.contact.services

import com.tygrupp.phonebook.contact.exception.PhoneBookException
import com.tygrupp.phonebook.contact.models.Contact
import com.tygrupp.phonebook.contact.models.Entry
import com.tygrupp.phonebook.contact.repository.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContactServiceImpl @Autowired constructor (
    private val contactRepository : ContactRepository) : ContactService {
    override fun createContact(entry: Entry): Contact {
        if (entry.equals(null)) throw PhoneBookException("Fields cannot be empty")
        val contact = Contact(entry.name, entry.phoneNumber, entry.address)
        return contactRepository.save(contact)
    }

    override fun findContactByPhoneNumber(phoneNumber: String): Optional<Contact>? {
        if (phoneNumber.equals(null)) throw PhoneBookException("Phonenumber field is empty")
        return contactRepository.findByPhoneNumber(phoneNumber)
    }


    override fun findContactByName(name: String): Iterable<Contact> {
        return contactRepository.findByName(name)
    }

    override fun findAllContact(): List<Contact>? {
        return contactRepository.findAll()
    }

    override fun deleteContact(phoneNumber: String) {
        if (phoneNumber.equals(null)) throw PhoneBookException("Phonenumber field is empty")
        val contactList = contactRepository.findByPhoneNumber(phoneNumber)
        if (contactList!!.equals(null)) throw PhoneBookException("Contact does not exist")
        contactList.get().id?.let { contactRepository.deleteById(it) }
    }
}