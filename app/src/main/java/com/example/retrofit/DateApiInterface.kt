package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface DateApiInterface {
    @GET("api/v1/rp1serrhhbdb3/")
    fun getDate(): Call<Array<DateProperty>>
}