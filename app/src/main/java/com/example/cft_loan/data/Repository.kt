package com.example.cft_loan.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cft_loan.LoanApp
import com.example.cft_loan.data.entities.LoanList
import com.example.cft_loan.data.entities.User
import com.example.cft_loan.data.entities.UserBuilder
import com.example.cft_loan.data.entities.UserInfo
import com.example.cft_loan.data.local.dao.LoanDao
import com.example.cft_loan.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors
import javax.inject.Inject

class Repository {
    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var loanDao: LoanDao

    init {
        LoanApp.appComponents.inject(this)
    }

    private var loanList: MutableLiveData<LoanList> = MutableLiveData()

    fun getUserData(): LiveData<User> = loanDao.getUserData()
    fun getLoanList(): MutableLiveData<LoanList> = loanList

    fun registerUser(userInfo: UserInfo) {
        apiService.registerUser(userInfo).enqueue(object: Callback<UserInfo>{
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                response.isSuccessful.apply { loginUser(userInfo) }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {

            }
        })
    }

    fun loginUser(userInfo: UserInfo) {
        apiService.loginUser(userInfo).enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                response.isSuccessful.apply {
                    Log.i("ssssss", response.toString())
                    saveDataToDb(UserBuilder()
                            .setName(userInfo.name)
                            .setPassword(userInfo.password)
                            .setToken(response.body().toString())
                            .build()
                    )
                 }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
    }

    fun getLoanList(token: String) {
        apiService.getLoansList(token).enqueue(object: Callback<LoanList>{
            override fun onResponse(call: Call<LoanList>, response: Response<LoanList>) {
                response.isSuccessful.apply {
                    loanList.value = response.body()
                }
            }

            override fun onFailure(call: Call<LoanList>, t: Throwable) {

            }
        })
    }

    private fun saveDataToDb(userData: User) {
        Executors.newSingleThreadExecutor().execute(
                fun () {loanDao.saveUserData(userData)}
        )
    }
}