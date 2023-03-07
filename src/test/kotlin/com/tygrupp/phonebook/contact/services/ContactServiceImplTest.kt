package com.tygrupp.phonebook.contact.services

import com.tygrupp.phonebook.contact.models.Contact
import com.tygrupp.phonebook.contact.models.Entry
import com.tygrupp.phonebook.contact.repository.ContactRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("dev")
//@Execution(ExecutionMode.CONCURRENT)
class ContactServiceImplTest {

    @Autowired
    private lateinit var  contactService : ContactService
    @Autowired
    private lateinit var contactRepository : ContactRepository
    private lateinit var contact : Contact

    @BeforeEach
    fun setUp() {
        contact = contactService.createContact(
            Entry("Olukunbi","09069390202", "No 1, Road 3f")
        )
    }

    @Test
    fun testThatContactExist() {
        val contact = Contact("Olukunbi","09069390202", "No 1, Road 3f")
        assertThat(contact).isNotNull
    }

    @Test
    fun test_ThatContactService_CanCreateContact() {
        val contactService = ContactServiceImpl(contactRepository)
        val contact = contactService.createContact(Entry("Olukunbi","09069390202", "No 1, Road 3f"))
        assertEquals("Olukunbi", contact.name)
        assertEquals("09069390202", contact.phoneNumber)
        assertEquals("No 1, Road 3f", contact.address)
    }

//    @Test
//    @Execution(ExecutionMode.CONCURRENT)
//    fun test_ThatContactCreated_IsPersisted() {
//        assertThat(contactRepository.count()).isEqualTo(1L)
//    }
//    @Test
//    fun test_ThatContact_CanBeRetrieved_ByPhoneNumber() {
//        val retrievedContact = contactService.findContactByPhoneNumber("09069390202")
//        val repositoryContact = contactRepository.findByPhoneNumber("09069390202")
//        assertThat(retrievedContact?.get()?.name).isEqualTo("Olukunbi")
//        assertThat(retrievedContact?.get()?.name).isEqualTo(repositoryContact?.get()?.name)
//    }

    @Test
    fun test_ThatContact_CanBeRetrieved_ByName() {
        val retrievedContacts = contactService.findContactByName("Olukunbi")
        assertThat(retrievedContacts!!::class).isEqualTo(ArrayList::class)
    }

    @Test
    fun test_ThatMultipleContact_WithSameName_CanBeRetrieved() {
        contactService.createContact(Entry("Olukunbi","08109039384", "No 1, Road 3a"))
        val retrievedContacts = contactService.findContactByName("Olukunbi")
        val repositoryContacts = contactRepository.findByName("Olukunbi")
        assertThat(retrievedContacts?.count()).isEqualTo(2L)
        assertThat(retrievedContacts?.count()).isEqualTo(repositoryContacts?.count())
    }

//    @Test
//    @Execution(ExecutionMode.CONCURRENT)
//    fun test_That_AllContacts_CanBeGotten() {
//        contactService.createContact(Entry("Olukunbi","08109039384", "No 1, Road 3a"))
//        contactService.createContact(Entry("Adewale","08100049384", "No 1, Road 3a"))
//        val allContacts = contactService.findAllContact()
//        assertThat(allContacts?.count()).isEqualTo(3L)
//    }

//    @Test
//    fun test_ThatContact_CanBeDeleted() {
//        contactService.deleteContact("09069390202")
//        assertThat(contactService.findContactByPhoneNumber("09069390202")).isEmpty
//    }

}