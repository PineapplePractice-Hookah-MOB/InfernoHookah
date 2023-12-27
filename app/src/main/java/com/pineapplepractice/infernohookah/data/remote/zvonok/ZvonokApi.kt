package com.pineapplepractice.infernohookah.data.remote.zvonok

import com.pineapplepractice.infernohookah.data.remote.NetworkApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ZvonokApi: NetworkApi{

    @GET("tellcode")
    override fun tellCode(): Call<ResponseBody>

}