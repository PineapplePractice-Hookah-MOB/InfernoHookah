package com.pineapplepractice.infernohookah.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface HookahApi {
    @GET("api/login")
    fun getApi(): Call<ResponseBody>

    object ApiConst {
        const val BASE_URL = "https://infernolounge5.hookah.work/"
    }
}


