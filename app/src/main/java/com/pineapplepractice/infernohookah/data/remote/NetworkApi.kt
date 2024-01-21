package com.pineapplepractice.infernohookah.data.remote

import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.data.datamodels.BookingResponse
import com.pineapplepractice.infernohookah.data.datamodels.DishMenuResponse
import com.pineapplepractice.infernohookah.data.datamodels.TokenResponse
import com.pineapplepractice.infernohookah.data.datamodels.UserAuthRequest
import com.pineapplepractice.infernohookah.data.datamodels.UserRequest
import com.pineapplepractice.infernohookah.data.datamodels.UserResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    fun tellCode(): Call<ResponseBody>

    @POST("auth/authenticate")
    fun auth(@Body user: UserAuthRequest): Call<TokenResponse>

    @POST("auth/register")
    fun saveUser(@Body user: UserRequest): Call<ResponseBody>

    @GET("users/all")
    fun getAllUsers(): Call<List<UserResponse>>

    @GET("menu")
    fun getDishMenu(
        @Header("Authorization") token: String,
        @Query("from") from: Int,
        @Query("size") size: Int
    ): Call<List<DishMenuResponse>>

    @POST("booking")
    fun createPost(
        @Header("Authorization") token: String,
        @Body bookingRequest: BookingRequest
    ): Call<ResponseBody>

//    @GET("booking/{userId}")

    @GET("booking/{userId}")
    fun getBookingByUserId(
        @Path("userId") userId: String,
        @Header("Authorization") token: String
    ): Call<List<BookingResponse>>

}
