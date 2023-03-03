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
     * @return Contact
     */
    fun findByPhoneNumber(phoneNumber: String): Optional<Contact>?

    @Query("SELECT e FROM Contact e WHERE e.name LIKE CONCAT('%',:name,'%')")
    fun findByName(name: String): Iterable<Contact>

}