package com.pineapplepractice.infernohookah.data.repository

import arrow.core.getOrElse
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.data.Category
import com.pineapplepractice.infernohookah.data.Dishes
import com.pineapplepractice.infernohookah.data.TypeOfDishes
import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.data.datamodels.BookingResponse
import com.pineapplepractice.infernohookah.data.datamodels.DishMenuResponse
import com.pineapplepractice.infernohookah.data.datamodels.TokenResponse
import com.pineapplepractice.infernohookah.data.datamodels.UserAuthRequest
import com.pineapplepractice.infernohookah.data.datamodels.UserRequest
import com.pineapplepractice.infernohookah.data.datamodels.UserResponse
import com.pineapplepractice.infernohookah.data.remote.NetworkApi
import com.pineapplepractice.infernohookah.data.storage.Storage
import com.pineapplepractice.infernohookah.domain.models.PhoneNumber
import com.pineapplepractice.infernohookah.domain.models.RootDishes
import com.pineapplepractice.infernohookah.domain.models.User
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository
import io.github.nefilim.kjwt.JWT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.awaitResponse

class MainRepositoryImpl(
    private val storage: Storage,
    private val networkApi: NetworkApi,
//    private val bookingApi: BookingApi
//    private val infernoDao: InfernoDao
) : MainRepository {

    override suspend fun authUser(user: User): TokenResponse? {
        var idUser = ""

        try {
            val response = networkApi.auth(mapDomainUserToDataUserAuthRequest(user)).awaitResponse()

            if (response.isSuccessful) {
                val responseBody: TokenResponse? = response.body()
                println("Теперь у вас есть тело ответа, которое вы можете прочитать или обработать ")
                println("body : $responseBody ")

                println("response: $response")
                println("response.isSuccessful: ${response.isSuccessful}")
                println("response.body: ${response.body()}")
                println("response.code: ${response.code()}")
                println("response.headers: ${response.headers()}")
                println("response.errorBody: ${response.errorBody()}")
                println("response.message: ${response.message()}")
                println("response.raw: ${response.raw()}")

                val token = response.body()
//                val decodedJWT = JWT.decode(token?.access_token)

                /*                println(
                                    "ид из токена:${token?.access_token} = ${
                                        decodedJWT.getClaim("id").asString()
                                    }"
                                )
                                println(
                                    "ид из токена:${token?.access_token} = ${
                                        decodedJWT.getClaim("tree").asMap()?.get("id")
                                    }"
                                    //                            .asObject(Map::class.java)?.get("id")
                                )*/

                if (token != null) {
                    storage.saveAccessToken(token.access_token)
                    storage.saveRefreshToken(token.refresh_token)
                    val decodeJWT = JWT.decode(token.access_token)
//                    val decodeJWT2 = JWT.decodeT(token.access_token, JWSHMAC256Algorithm)
                    println("decodeJWT: $decodeJWT")

                    decodeJWT.tap {
                        println("the subject is: ${it.claimValue("id")}")

                        idUser = it.claimValue("id").getOrElse { "" }
                    }

                    println("id =  $idUser")
                }

                if (idUser != "") {
                    storage.saveUserId(idUser)
                    return response.body()
                } else return null

                // Теперь у вас есть тело ответа, которое вы можете прочитать или обработать
            } else {
                println("Обработка ошибки")

                // Обработка ошибки
                val errorBody: ResponseBody? = response.errorBody()
                // Обработка ошибки на основе errorBody

            }
        } catch (e: Exception) {
            // Обработка исключения, возникшего при выполнении запроса
            println("e: Exception")
        }
        return null
    }

    override fun savePhoneToSharedPref(phoneNumber: PhoneNumber): Boolean {
        val result = storage.savePhone(phoneNumber)
        return result
    }

    override suspend fun saveUser(user: User): Boolean {
        val response = networkApi.saveUser(mapDomainUserToDataUserRequest(user)).awaitResponse()

        return response.isSuccessful
    }

    override suspend fun saveBooking(bookingRequest: BookingRequest): Boolean {

        /*        val bookingRequest = BookingRequest(
                    1,
                    1,
                    "Test 11",
                    "2023-12-28 20:00:00",
                    "2023-12-28 21:00:00"
                )*/

//        val response = bookingApi.createPost(bookingRequest).awaitResponse()
        val response =
            networkApi.createPost("Bearer ${storage.getAccessToken()}", bookingRequest)
                .awaitResponse()

        return response.isSuccessful
    }

    override suspend fun getBookingByUserId(id: Int): BookingResponse? {
        var booking: List<BookingResponse>? = null

        /*        try {
                    withContext(Dispatchers.IO) {
                        booking = bookingApi.getBookingByUserId()

                        println("GetBookingByUserId: $booking")
        //                list?.let { repo.putToDb(it) }
                        return@withContext booking!![0]
                    }


                } catch (e: HttpException) {
                    // Обработка ошибок HTTP (например, 404, 500 и т.д.)
                    println("HTTP Exception: ${e.code()}")
        //            return null
                } catch (e: Exception) {
                    // Обработка остальных исключений
                    println("Exception: $e")
        //            return null
                }*/

        try {
//            val response = bookingApi.getBookingByUserId().awaitResponse()
            val response = networkApi.getBookingByUserId(
                storage.getUserId(),
                "Bearer ${storage.getAccessToken()}"
            ).awaitResponse()
            /*            val response =
                            networkApi.getBookingByUserId("Bearer ${storage.getAccessToken()}").awaitResponse()*/


            if (response.isSuccessful) {
//                    val responseBody: ResponseBody? = response.body()
                val responseBody: List<BookingResponse>? = response.body()
                println("Теперь у вас есть тело ответа, которое вы можете прочитать или обработать ")
                println("body : $responseBody ")

                println("response: $response")
                println("response.isSuccessful: ${response.isSuccessful}")
                println("response.body: ${response.body()}")
                println("response.code: ${response.code()}")
                println("response.headers: ${response.headers()}")
                println("response.errorBody: ${response.errorBody()}")
                println("response.message: ${response.message()}")
                println("response.raw: ${response.raw()}")
                println(response.body()?.get(0))
                println(response.body()?.first())
                println(response.body()?.last())

                return response.body()?.last()

                // Теперь у вас есть тело ответа, которое вы можете прочитать или обработать
            } else {
                println("Обработка ошибки")

                // Обработка ошибки
                val errorBody: ResponseBody? = response.errorBody()
                // Обработка ошибки на основе errorBody
            }
        } catch (e: Exception) {
            // Обработка исключения, возникшего при выполнении запроса
            println("e: Exception")

        }

        return null
    }

    override suspend fun getAllUser(): List<UserResponse>? {
        try {
//            val response = networkApi.getAllUsers().awaitResponse()
            val response = networkApi.getAllUsers().awaitResponse()

            if (response.isSuccessful) {
//                    val responseBody: ResponseBody? = response.body()
                val responseBody: List<UserResponse>? = response.body()
                println("Теперь у вас есть тело ответа, которое вы можете прочитать или обработать ")
                println("body : $responseBody ")

                println("response: $response")
                println("response.isSuccessful: ${response.isSuccessful}")
                println("response.body: ${response.body()}")
                println("response.code: ${response.code()}")
                println("response.headers: ${response.headers()}")
                println("response.errorBody: ${response.errorBody()}")
                println("response.message: ${response.message()}")
                println("response.raw: ${response.raw()}")
                println(response.body()?.get(0))
                println(response.body()?.first())
                println(response.body()?.last())

                return response.body()

                // Теперь у вас есть тело ответа, которое вы можете прочитать или обработать
            } else {
                println("Обработка ошибки")

                // Обработка ошибки
                val errorBody: ResponseBody? = response.errorBody()
                // Обработка ошибки на основе errorBody
            }
        } catch (e: Exception) {
            // Обработка исключения, возникшего при выполнении запроса
            println("e: Exception")

        }

        return null
    }

    override suspend fun getUserIdFromStorage(): String {
        return storage.getUserId()
    }

    override suspend fun getUserByLogin(login: String): User? {
        return withContext(Dispatchers.IO) {
            val listUser = async { getAllUser() }.await()
//            val listUser = getAllUser()

            println("listUser: login = ${listUser?.get(0)?.login}")


            val user = listUser?.find { it.login == login }

            println("user: login = ${user?.login}")

            user?.let {
                return@let mapDataUserResponseToDomainUser(it)
            } ?: run {
                return@run null
            }
        }
    }

    //    override suspend fun getDishMenuFromApi(): Map<Pair<Int, String>, List<DishMenuResponse>>? {
    override suspend fun getDishMenuFromApi(): RootDishes? {
        try {
            val response =
                networkApi.getDishMenu("Bearer ${storage.getAccessToken()}", 0, 30).awaitResponse()

            if (response.isSuccessful) {
                val responseBody: List<DishMenuResponse>? = response.body()
                println("Теперь у вас есть тело ответа, которое вы можете прочитать или обработать ")
                println("body : $responseBody ")

                println("response: $response")
                println("response.isSuccessful: ${response.isSuccessful}")
                println("response.body: ${response.body()}")
                println("response.code: ${response.code()}")
                println("response.headers: ${response.headers()}")
                println("response.errorBody: ${response.errorBody()}")
                println("response.message: ${response.message()}")
                println("response.raw: ${response.raw()}")
                println(response.body()?.get(0))
                println(response.body()?.first())
                println(response.body()?.last())

//                response.body()?.let { mapDataDishMenuResponseToDomainRootDishes(it) }
                return response.body()?.let { mapDataDishMenuResponseToDomainRootDishes(it) }

                // Теперь у вас есть тело ответа, которое вы можете прочитать или обработать
            } else {
                println("Обработка ошибки")

                // Обработка ошибки
                val errorBody: ResponseBody? = response.errorBody()
                // Обработка ошибки на основе errorBody
            }
        } catch (e: Exception) {
            // Обработка исключения, возникшего при выполнении запроса
            println("e: Exception")

        }

        return null
    }

    /*    override suspend fun getUserByLogin(login: String): User? {
            return withContext(Dispatchers.IO) {

            }*/
//        }

//        val listUser = async { getAllUser() }.await()
    /*        val listUser = getAllUser()
            val user = listUser?.find { it.login == login }

            user?.let {
                return mapToDomainUserResponseToUser(it)
            } ?: run {
                return null
            }
}*/

    private fun mapDomainUserToDataUserRequest(user: User): UserRequest {
        return UserRequest(
            name = user.login,
//            birthday = user.birthday,
            birthday = "1975-12-12",
            email = user.email,
            password = user.pass
        )
    }

    private fun mapDomainUserToDataUserAuthRequest(user: User): UserAuthRequest {
        return UserAuthRequest(
            email = user.email,
            password = user.pass
        )
    }

    private fun mapDataUserResponseToDomainUser(user: UserResponse): User {
        return User(
            email = user.email,
            login = user.login,
            birthday = "",
//            birthday = "${user.birthday[0]}-${user.birthday[1]}-${user.birthday[2]}",
            pass = ""
        )
    }

    private fun mapDataDishMenuResponseToDomainRootDishes(dishMenu: List<DishMenuResponse>)
            : RootDishes {
        val listMutableCategory = mutableListOf<Category>().apply {
            add(Category(id = 0, idType = 0, name = "Все"))
            add(Category(id = 1, idType = 1, name = "Все"))
        }

        val listMutableDishes = mutableListOf<Dishes>()

        val groupedDishes = dishMenu.groupBy { dish ->
            val words = dish.name.split(", ")
            if (words.size > 1) {
                Pair(dish.foodCategoryId, words[1])
            } else {
                Pair(dish.foodCategoryId, "")
            }
        }

        var idCategory = 2
        var idDishes = 0
        groupedDishes.forEach { (key, value) ->
            val (firstElement, secondElement) = key
            value.forEach { dish ->
                listMutableDishes.add(
                    Dishes(
                        id = idDishes++,
                        idType = firstElement - 1,
                        name = dish.name.split(", ")[0],
                        description = dish.name.split(", ")[1],
                        count = dish.quantity.toInt(),
                        price = dish.price.toInt(),
                        image = 2131230903,
                        urlImage = dish.picLink
                    )
                )
            }

            listMutableCategory.add(
                Category(
                    id = idCategory++,
                    idType = firstElement - 1,
                    name = secondElement
                )
            )
        }

        val listTypeOfDishes = listOf(
            TypeOfDishes(id = 0, name = "Чай", image = R.drawable.ic_tea_grey),
            TypeOfDishes(id = 1, name = "Кальян", image = R.drawable.ic_hookah_white),
        )
/*        val listOfCategory = listOf(
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
        val listOfDishes = listOf(
            Dishes(
                id = 1,
                idType = 0,
                name = "Облепиховый",
                description = "Фирменный",
                count = 900,
                price = 350,
                image = R.drawable.img_tea_10
            ),
            Dishes(
                id = 2,
                idType = 0,
                name = "Клюквенный",
                description = "Фирменный",
                count = 900,
                price = 350,
                image = R.drawable.img_tea_16
            ),
            Dishes(
                id = 3,
                idType = 0,
                name = "Яблочный",
                description = "Фирменный",
                count = 900,
                price = 350,
                image = R.drawable.img_tea_3
            ),
            Dishes(
                id = 4,
                idType = 0,
                name = "Вишневый",
                description = "Фирменный",
                count = 900,
                price = 350,
                image = R.drawable.img_tea_13
            ),
            Dishes(
                id = 5,
                idType = 0,
                name = "Клубничный",
                description = "Фирменный",
                count = 900,
                price = 350,
                image = R.drawable.img_tea_20
            ),
            Dishes(
                id = 6,
                idType = 0,
                name = "Пу-Эр",
                description = "Вкусовой",
                count = 600,
                price = 250,
                image = R.drawable.img_tea_22
            ),
            Dishes(
                id = 7,
                idType = 0,
                name = "Наглый фрукт",
                description = "Вкусовой",
                count = 600,
                price = 200,
                image = R.drawable.img_tea_15
            ),
            Dishes(
                id = 8,
                idType = 0,
                name = "Саган Дайля",
                description = "Вкусовой",
                count = 600,
                price = 200,
                image = R.drawable.img_tea_18
            ),
            Dishes(
                id = 9,
                idType = 0,
                name = "Ягодный",
                description = "Вкусовой",
                count = 600,
                price = 200,
                image = R.drawable.img_tea_9
            ),
            Dishes(
                id = 10,
                idType = 0,
                name = "Да Хун Пао",
                description = "Улун",
                count = 600,
                price = 250,
                image = R.drawable.img_tea_4
            ),
            Dishes(
                id = 11,
                idType = 0,
                name = "Тегуань Инь",
                description = "Улун",
                count = 600,
                price = 200,
                image = R.drawable.img_tea_11
            ),
            Dishes(
                id = 12,
                idType = 0,
                name = "Апельсиновый",
                description = "Улун",
                count = 600,
                price = 200,
                image = R.drawable.img_tea_2
            ),
            Dishes(
                id = 13,
                idType = 0,
                name = "Клубничный",
                description = "Улун",
                count = 600,
                price = 200,
                image = R.drawable.img_tea_7
            ),
            Dishes(
                id = 14,
                idType = 0,
                name = "Ганпаудер",
                description = "Зеленый",
                count = 600,
                price = 150,
                image = R.drawable.img_tea_5
            ),
            Dishes(
                id = 15,
                idType = 0,
                name = "Сенча",
                description = "Зеленый",
                count = 600,
                price = 150,
                image = R.drawable.img_tea_6
            ),
            Dishes(
                id = 16,
                idType = 0,
                name = "Жасминовый",
                description = "Зеленый",
                count = 600,
                price = 180,
                image = R.drawable.img_tea_14
            ),
            Dishes(
                id = 17,
                idType = 0,
                name = "С мелисой",
                description = "Зеленый",
                count = 600,
                price = 180,
                image = R.drawable.img_tea_1
            ),
            Dishes(
                id = 18,
                idType = 0,
                name = "Белый храм",
                description = "Черный",
                count = 600,
                price = 150,
                image = R.drawable.img_tea_21
            ),
            Dishes(
                id = 19,
                idType = 0,
                name = "Граф Грей",
                description = "Черный",
                count = 600,
                price = 150,
                image = R.drawable.img_tea_12
            ),
            Dishes(
                id = 20,
                idType = 0,
                name = "Пик Адама",
                description = "Черный",
                count = 600,
                price = 150,
                image = R.drawable.img_tea_17
            ),
            Dishes(
                id = 21,
                idType = 0,
                name = "Липовый мед",
                description = "Черный",
                count = 600,
                price = 180,
                image = R.drawable.img_tea_19
            ),
            Dishes(
                id = 22,
                idType = 0,
                name = "С чабрецом",
                description = "Черный",
                count = 600,
                price = 180,
                image = R.drawable.img_tea_8
            ),
            Dishes(
                id = 23,
                idType = 1,
                name = "Стандарт",
                description = "Стандарт",
                count = 1,
                price = 900,
                image = R.drawable.img_hookah_1
            ),
            Dishes(
                id = 24,
                idType = 1,
                name = "Премиум",
                description = "Премиум",
                count = 1,
                price = 1100,
                image = R.drawable.img_hookah_2
            ),
            Dishes(
                id = 25,
                idType = 1,
                name = "Эксклюзив",
                description = "Премиум",
                count = 1,
                price = 1100,
                image = R.drawable.img_hookah_4
            ),
            Dishes(
                id = 26,
                idType = 1,
                name = "Перезабивка",
                description = "Перезабивка",
                count = 1,
                price = 450,
                image = R.drawable.img_hookah_3
            )

        )*/

        val rootDishes = RootDishes(
            listTypeOfDishes = listTypeOfDishes,
            listCategory = listMutableCategory.toList(),
            listDishes = listMutableDishes.toList()
        )
        return rootDishes

    }
}