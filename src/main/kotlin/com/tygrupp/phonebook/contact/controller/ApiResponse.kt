package com.tygrupp.phonebook.contact.controller

import lombok.*

@NoArgsConstructor
@RequiredArgsConstructor
@Builder
data class ApiResponse(
    @NonNull private val message: String,
    @NonNull private val status: String?,
    private val data: Any?
    )
