package com.tygrupp.phonebook.contact.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import lombok.NonNull
import lombok.Setter

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
class Contact(
    val name: String,
    @NonNull
    val phoneNumber: String,
    val address: String,
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null
)