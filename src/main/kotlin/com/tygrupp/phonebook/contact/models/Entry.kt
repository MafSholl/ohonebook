package com.tygrupp.phonebook.contact.models

import java.io.Serializable
import java.time.LocalDateTime
class Entry(
		var name: String,
		var phoneNumber: String,
		val address: String,
		var entryTime: LocalDateTime = LocalDateTime.now(),
		var modifiedTime: LocalDateTime = LocalDateTime.now(),
) : Serializable