package com.pineapplepractice.infernohookah.data.datamodels

data class BookingRequest(
    val tableId: Int,
    val people: Int,
    val comment: String,
    val bookedFrom: String,
    val bookedTill: String
 )
