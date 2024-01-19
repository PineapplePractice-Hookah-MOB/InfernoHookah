package com.pineapplepractice.infernohookah.domain

import com.pineapplepractice.infernohookah.data.HookahApi
import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.data.remote.booking.BookingApi
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.awaitResponse

class HookahInteractor(
    private val retrofitService: HookahApi,
    private val repo: MainRepository,
    private val bookingApi: BookingApi
) {
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun getApi() {
        scope.launch {
            try {
//                val response = retrofitService.getApi().awaitResponse()
                val bookingRequest = BookingRequest(
                    2,
                    4,
                    2,
                    "Android test booking",
                    "2023-12-22 22:04:30",
                    "2023-12-22 22:45:30"
                )

                val response = bookingApi.createPost(bookingRequest).awaitResponse()
//                val response = bookingApi.getFreeOnDate("2023-12-22").awaitResponse()

                println("response : $response")

                if (response.isSuccessful) {
//                    val responseBody: ResponseBody? = response.body()
                    println("Теперь у вас есть тело ответа, которое вы можете прочитать или обработать ")
                    // Теперь у вас есть тело ответа, которое вы можете прочитать или обработать
                } else {
                    println("Обработка ошибки")

                    // Обработка ошибки
                    val errorBody: ResponseBody? = response.errorBody()
                    // Обработка ошибки на основе errorBody
                }
            } catch (e: Exception) {
                // Обработка исключения, возникшего при выполнении запроса
            }
        }

    }
}