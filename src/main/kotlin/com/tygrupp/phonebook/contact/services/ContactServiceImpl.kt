package com.tygrupp.phonebook.contact.services

import com.tygrupp.phonebook.contact.exception.exceptions.PhoneBookException
import com.tygrupp.phonebook.contact.models.Contact
import com.tygrupp.phonebook.contact.models.Entry
import com.tygrupp.phonebook.contact.repository.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContactServiceImpl @Autowired constructor (
    private val contactRepository : ContactRepository) : ContactService {
    /**
     * This method creates a new contact object and then saves it inside the repository.
     * It first checks if the object of @param entry is null or not.
     * It returns an object of Contact returned by the repository's save method
     * @param entry
     * @return Contact
     */
    override fun createContact(entry: Entry): Contact {
        if (entry.equals(null)) throw PhoneBookException("Fields cannot be empty")
        val contact = Contact(entry.name, entry.phoneNumber, entry.address)
        return contactRepository.save(contact)
    }

    /**
     * This method finds a contact inside the repository using the phonenumber.
     * Checks if the phonenumber object is null or not.
     * It returns a Generic Optional object of type Contact to prevent null pointer exceptions.
     * @param phoneNumber
     * @return Optional<Contact>
     */
    override fun findContactByPhoneNumber(phoneNumber: String): Optional<Contact>? {
        if (phoneNumber.equals(null)) throw PhoneBookException("Phonenumber field is empty")
        return contactRepository.findByPhoneNumber(phoneNumber)
    }

    /**
     *Finds a contact or list of contacts with a name matching the parameter
     * Throws exception on a null parameter object
     * @param name
     * @return Iterable<Contact>
     */
    override fun findContactByName(name: String): List<Contact>? {
        if (name.equals(null)) throw PhoneBookException("Phonenumber field is empty")
        return contactRepository.findByName(name)
    }

    /**
     * Finds all contact from the repository. Takes no parameter
     *
     * @return List<Contact>
     */
    override fun findAllContact(): List<Contact>? {
        return contactRepository.findAll()
    }

    /**
     * Deletes a contact from the repository that matches a phonenumber.
     * It uses the id of a contact with the phonenumber exists.
     */
    override fun deleteContact(phoneNumber: String) {
        if (phoneNumber.equals(null)) throw PhoneBookException("Phonenumber field is empty")
        val contactList = contactRepository.findByPhoneNumber(phoneNumber)
        if (contactList!!.equals(null)) throw PhoneBookException("Contact does not exist")
        contactList.get().id?.let { contactRepository.deleteById(it) }
    }
}