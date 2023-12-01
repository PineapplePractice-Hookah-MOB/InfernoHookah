package com.pineapplepractice.infernohookah.data

import com.pineapplepractice.infernohookah.R

data class TypeOfDishes(
    val id: Int,
    var name: String,
    val image: Int
)

val listOfTypeOfDishes = listOf(
    TypeOfDishes(id = 0, name = "Чай", image = R.drawable.ic_tea_grey),
    TypeOfDishes(id = 1, name = "Кальян", image = R.drawable.ic_hookah_white),
)
