package com.tygrupp.phonebook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PhonebookApplication

fun main(args: Array<String>) {
	runApplication<PhonebookApplication>(*args)
}
