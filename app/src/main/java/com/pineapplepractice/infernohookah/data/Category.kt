package com.pineapplepractice.infernohookah.data

data class Category(
    var id: Int,
    var idType: Int,
    val name: String
)

val listOfCategory = listOf(
    Category(id = 0, idType = 0, name = "Все"),
    Category(id = 1, idType = 1, name = "Все"),
    Category(id = 2, idType = 0, name = "Фирменный"),
    Category(id = 3, idType = 1, name = "Стандарт"),
    Category(id = 4, idType = 0, name = "Вкусовой"),
    Category(id = 5, idType = 1, name = "Премиум"),
    Category(id = 6, idType = 0, name = "Улун"),
    Category(id = 7, idType = 1, name = "Эксклюзив"),
    Category(id = 8, idType = 0, name = "Зеленый"),
    Category(id = 9, idType = 1, name = "Перезабивка"),
    Category(id = 10, idType = 0, name = "Черный")
)

/*val listOfHookahCategory = listOf(
    Category(id = 0, name = "Все"),
    Category(id = 1, name = "Стандарт"),
    Category(id = 2, name = "Премиум"),
    Category(id = 3, name = "Эксклюзив"),
    Category(id = 4, name = "Перезабивка")
)*/


