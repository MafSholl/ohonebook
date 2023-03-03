package com.tygrupp.phonebook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = [ "com.tygrupp.phonebook.*"] )
class PhonebookApplication

fun main(args: Array<String>) {
    runApplication<PhonebookApplication>(*args)
}
