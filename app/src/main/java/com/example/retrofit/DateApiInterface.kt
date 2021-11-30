package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface DateApiInterface {
    @GET(".")
    fun getDate(): Call<Array<DateProperty>>
}