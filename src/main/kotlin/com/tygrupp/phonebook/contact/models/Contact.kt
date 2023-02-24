package com.tygrupp.phonebook.contact

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.NonNull
import lombok.Setter

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
class Contact (
    @Id @GeneratedValue
    var id: Long,
    val name: String,
    @NonNull @Column(unique = true)
    val phoneNumber: String,
    val address: String
)