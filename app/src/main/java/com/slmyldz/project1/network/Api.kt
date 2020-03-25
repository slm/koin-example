package com.slmyldz.project1.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("users")
    fun getUsers(@Query("page") page:Int): Call<Page>

}