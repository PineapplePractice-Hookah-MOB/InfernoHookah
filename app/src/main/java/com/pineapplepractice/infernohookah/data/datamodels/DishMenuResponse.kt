package com.pineapplepractice.infernohookah.data.datamodels

data class DishMenuResponse(
    val id: Int,
    val name: String,
    val quantity: Float,
    val price: Float,
    val foodCategoryId: Int,
    val picLink: String
)
