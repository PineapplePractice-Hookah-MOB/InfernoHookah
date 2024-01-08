package com.pineapplepractice.infernohookah.data

data class Category(
    var id: Int,
    val name: String
)

val listOfCategory = listOf(
    Category(id = 0, name = "Все"),
    Category(id = 1, name = "Фирменный"),
    Category(id = 2, name = "Вкусовой"),
    Category(id = 3, name = "Улун"),
    Category(id = 4, name = "Зеленый"),
    Category(id = 5, name = "Черный")
)

val listOfHookahCategory = listOf(
    Category(id = 0, name = "Все"),
    Category(id = 1, name = "Стандарт"),
    Category(id = 2, name = "Премиум"),
    Category(id = 3, name = "Эксклюзив"),
    Category(id = 4, name = "Перезабивка")
)


