package com.example.cft_loan.data.remote

import com.example.cft_loan.data.entities.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("registration")
    fun registerUser(@Body userInfo: UserInfo): Call<UserInfo>

    @POST("login")
    fun loginUser(@Body userInfo: UserInfo): Call<String>

    @POST("loans")
    fun postLoan(@Header("Authorization") token: String, @Body loan: PostLoan): Call<Loan>

    @GET("loans/conditions")
    fun getLoansConditions(@Header("Authorization") token: String): Call<LoanCondition>

    @GET("loans/all")
    fun getLoansList(@Header("Authorization") token: String): Call<LoanList>

}