package com.pineapplepractice.infernohookah.data.repository

import com.pineapplepractice.infernohookah.data.dao.InfernoDao
import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.data.datamodels.BookingResponse
import com.pineapplepractice.infernohookah.data.remote.NetworkApi
import com.pineapplepractice.infernohookah.data.remote.booking.BookingApi
import com.pineapplepractice.infernohookah.data.storage.Storage
import com.pineapplepractice.infernohookah.domain.models.PhoneNumber
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.awaitResponse

class MainRepositoryImpl(
    private val storage: Storage,
    private val networkApi: NetworkApi? = null,
    private val bookingApi: BookingApi
//    private val infernoDao: InfernoDao
) : MainRepository {

    override fun savePhoneToSharedPref(phoneNumber: PhoneNumber): Boolean {
        val result = storage.savePhone(phoneNumber)
        return result
    }

    override suspend fun SaveBooking(bookingRequest: BookingRequest) :Boolean {

/*        val bookingRequest = BookingRequest(
            1,
            1,
            "Test 11",
            "2023-12-28 20:00:00",
            "2023-12-28 21:00:00"
        )*/

        val response = bookingApi.createPost(bookingRequest).awaitResponse()

        return response.isSuccessful
    }

    override suspend fun GetBookingByUserId(id: Int): BookingResponse? {
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
            val response = bookingApi.getBookingByUserId().awaitResponse()


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
}