package com.pineapplepractice.infernohookah.data.repository

import arrow.core.getOrElse
import arrow.core.right
import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.data.datamodels.BookingResponse
import com.pineapplepractice.infernohookah.data.datamodels.TokenResponse
import com.pineapplepractice.infernohookah.data.datamodels.UserAuthRequest
import com.pineapplepractice.infernohookah.data.datamodels.UserRequest
import com.pineapplepractice.infernohookah.data.datamodels.UserResponse
import com.pineapplepractice.infernohookah.data.remote.NetworkApi
import com.pineapplepractice.infernohookah.data.remote.booking.BookingApi
import com.pineapplepractice.infernohookah.data.storage.Storage
import com.pineapplepractice.infernohookah.domain.models.PhoneNumber
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
            val response = networkApi.getBookingByUserId(storage.getUserId(), "Bearer ${storage.getAccessToken()}").awaitResponse()
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
                return@let mapDataUserResponseToDataUser(it)
            } ?: run {
                return@run null
            }
        }
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

    private fun mapDataUserResponseToDataUser(user: UserResponse): User {
        return User(
            email = user.email,
            login = user.login,
            birthday = "",
//            birthday = "${user.birthday[0]}-${user.birthday[1]}-${user.birthday[2]}",
            pass = ""
        )
    }
}