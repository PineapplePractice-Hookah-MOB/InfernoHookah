package com.pineapplepractice.infernohookah.data.datamodels

data class UserResponse(
    val id: Int,
    val email: String,
    val login: String,
    val name: String,
    val phone: String,
    val comment: String,
    val card: String,
    val birthday: List<Int>,
    val groupIds: Int,
    val points: Float
)
