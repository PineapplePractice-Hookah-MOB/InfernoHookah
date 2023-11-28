package com.pineapplepractice.infernohookah.data

data class Category(
    var id: Int,
    val name: String
)

val listOfCategory = listOf(
    Category(id = 1, name = "Все"),
    Category(id = 2, name = "Фирменный"),
    Category(id = 3, name = "Вкусовой"),
    Category(id = 4, name = "Улун"),
    Category(id = 5, name = "Зеленый"),
    Category(id = 6, name = "Черный")
)

