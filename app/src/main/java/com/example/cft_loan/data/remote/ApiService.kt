package com.example.cft_loan.data.remote

import com.example.cft_loan.data.entities.Loan
import com.example.cft_loan.data.entities.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("loans/all")
    fun getLoansList(@Query("") token: String): Call<Loan>

    @POST("registration")
    fun createUser(@Body userInfo: UserInfo): Call<UserInfo>

    @POST("login")
    fun loginUser(@Body userInfo: UserInfo): Call<String>
}