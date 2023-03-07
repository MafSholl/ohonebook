package com.tygrupp.phonebook.contact.repository

import com.tygrupp.phonebook.contact.models.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ContactRepository : JpaRepository<Contact, Long> {

    /**
     * This method finds a contact from the repository by its phonenumber.
     * It returns an object of type Contact
     * @param phoneNumber of type String
     * @return Contact
     */
    fun findByPhoneNumber(phoneNumber: String): Optional<Contact>?

    /**
     * This method finds a single contact or more than from the database
     * that matches the name column of a contact record
     *
     * @return List of Contact
     */
    @Query("SELECT e FROM Contact e WHERE e.name LIKE CONCAT('%',:name,'%')")
    fun findByName(name: String): List<Contact>?
}