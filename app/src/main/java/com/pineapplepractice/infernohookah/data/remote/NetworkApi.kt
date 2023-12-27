package com.pineapplepractice.infernohookah.data.remote

import okhttp3.ResponseBody
import retrofit2.Call

interface NetworkApi {

    fun tellCode(): Call<ResponseBody>

}
