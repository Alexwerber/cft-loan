package com.example.cft_loan.data.remote

import com.example.cft_loan.data.entities.Loan
import com.example.cft_loan.data.entities.LoanCondition
import com.example.cft_loan.data.entities.LoanList
import com.example.cft_loan.data.entities.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("loans/all")
    fun getLoansList(@Query("Authorization: ") token: String): Call<LoanList>

    @POST("registration")
    fun registerUser(@Body userInfo: UserInfo): Call<UserInfo>

    @POST("login")
    fun loginUser(@Body userInfo: UserInfo): Call<String>

    @GET("loans/conditions")
    fun getLoansConditions(): Call<LoanCondition>

}