package com.pineapplepractice.infernohookah.domain.models

class PhoneNumber(
    val phoneNumberFull: String,
    val codePhoneNumber: String = "",
    val phoneNumberWithoutCode: String = ""
)