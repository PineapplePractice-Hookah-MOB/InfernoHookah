package com.pineapplepractice.infernohookah.domain

import com.pineapplepractice.infernohookah.data.HookahApi
import com.pineapplepractice.infernohookah.data.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.awaitResponse

class HookahInteractor (
    private val retrofitService: HookahApi,
    private val repo: MainRepository,
) {
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun getApi() {
        scope.launch {
            try {
                val response = retrofitService.getApi().awaitResponse()
                if (response.isSuccessful) {
                    val responseBody: ResponseBody? = response.body()
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