package com.example.cft_loan.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("/loans/all")
    fun getLoansList()
}