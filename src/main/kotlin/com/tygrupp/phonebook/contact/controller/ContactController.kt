package com.tygrupp.phonebook.contact.controller

import com.tygrupp.phonebook.contact.models.Contact
import com.tygrupp.phonebook.contact.models.Entry
import com.tygrupp.phonebook.contact.services.ContactService
import jakarta.servlet.http.HttpServletRequest
import lombok.NonNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.regex.Matcher
import java.util.regex.Pattern

@RestController
@RequestMapping("api/v1/phonebook")
class ContactController @Autowired constructor(private var contactService: ContactService) {

    /**
     * This method is to create a new contact
     * It returns an object of type Contact
     *
     * @return Contact
     */
    @PostMapping("/create-contact")
    fun saveContact(@RequestBody request: Entry, httpServletRequest: HttpServletRequest) : ResponseEntity<*> {
        val entry = Entry(request.name, request.phoneNumber, request.address)
        val contact : Contact = contactService.createContact(entry)
        return ResponseEntity.ok()
            .body(ApiResponse("Successful!",
                HttpStatus.OK.value().toString(),
                contact
                )
            )
    }

    @GetMapping("/search-contact")
    fun findAContact(@RequestBody @NonNull request: String) : ResponseEntity<*> {
        val expression = Pattern.compile(".\\d*")
        val matcher : Matcher = expression.matcher(request)
        var searchResult: Any? = null
        if (matcher.find()) {
            searchResult = contactService.findContactByPhoneNumber(request)?.get()
        } else {
            searchResult = contactService.findContactByName(request)
        }
        return ResponseEntity.ok()
            .body(ApiResponse("Successful!",
                HttpStatus.OK.value().toString(),
                searchResult
                ) 
            )
    }
    @GetMapping("/")
    fun findAllContact() : ResponseEntity<*> {
        val searchResult = contactService.findAllContact()
        return ResponseEntity.ok()
            .body(ApiResponse("Successful!",
                HttpStatus.OK.value().toString(),
                searchResult
                )
            )
    }

    @DeleteMapping("/delete-contact")
    fun deleteContact(@RequestBody @NonNull request: String) : ResponseEntity<*> {
        contactService.deleteContact(request)
        return ResponseEntity.ok()
            .body("Contact deleted successfully")
    }
}