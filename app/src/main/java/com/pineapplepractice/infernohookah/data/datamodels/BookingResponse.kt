package com.pineapplepractice.infernohookah.data.datamodels

data class BookingResponse(
    val bookingId: Int,
    val tableId: Int,
    val clientId: Int,
    val people: Int,
    val comment: String,
    val bookedFrom: String,
    val bookedTill: String
)
