package com.example.cft_loan.data

import com.example.cft_loan.LoanApp
import com.example.cft_loan.data.entities.UserInfo
import com.example.cft_loan.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository {
    @Inject
    private lateinit var apiService: ApiService

    init {
        LoanApp.appComponents.inject(this)
    }

    fun registerUser(userInfo: UserInfo) {
        apiService.registerUser(userInfo).enqueue(object: Callback<UserInfo>{
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {

            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {

            }
        })
    }

    fun loginUser(userInfo: UserInfo) {
        apiService.loginUser(userInfo).enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {

            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
    }
}