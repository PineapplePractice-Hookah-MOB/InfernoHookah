package com.pineapplepractice.infernohookah.data.remote

import com.pineapplepractice.infernohookah.data.datamodels.UserRequest
import com.pineapplepractice.infernohookah.data.datamodels.UserResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkApi {

    fun tellCode(): Call<ResponseBody>

    @POST("users")
    fun saveUser(@Body user: UserRequest): Call<ResponseBody>

    @GET("users/all")
    fun getAllUsers(): Call<List<UserResponse>>

}
