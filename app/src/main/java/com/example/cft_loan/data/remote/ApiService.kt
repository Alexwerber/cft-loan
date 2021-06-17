package com.example.cft_loan.data.remote

import com.example.cft_loan.data.entities.Loan
import com.example.cft_loan.data.entities.LoanCondition
import com.example.cft_loan.data.entities.LoanList
import com.example.cft_loan.data.entities.UserInfo
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("loans/all")
    fun getLoansList(@Header("Authorization") token: String): Call<LoanList>

    @POST("registration")
    fun registerUser(@Body userInfo: UserInfo): Call<UserInfo>

    @POST("login")
    fun loginUser(@Body userInfo: UserInfo): Call<String>

    @GET("loans/conditions")
    fun getLoansConditions(@Header("Authorization") token: String): Call<LoanCondition>

}