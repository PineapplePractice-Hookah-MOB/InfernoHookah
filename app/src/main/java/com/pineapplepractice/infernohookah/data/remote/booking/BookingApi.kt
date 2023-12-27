package com.pineapplepractice.infernohookah.data.remote.booking

import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.data.datamodels.BookingResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BookingApi {

    //формат даты 2023-12-22
    @GET("booking/free-on-date")
    fun getFreeOnDate(@Query("date") date: String): Call<ResponseBody>

    @GET("booking/free-on-table")
    fun getFreeOnDateAndTable(@Query("date") date: String, @Query("id") id: Int): Call<ResponseBody>

    @GET("booking/444")
//    fun getBookingByUserId(): List<BookingResponse>
//    fun getBookingByUserId(): Call<ResponseBody>
    fun getBookingByUserId(): Call<List<BookingResponse>>

    @POST("booking")
    fun createPost(@Body bookingRequest: BookingRequest): Call<ResponseBody>

}