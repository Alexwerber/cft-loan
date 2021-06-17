package com.example.cft_loan.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cft_loan.LoanApp
import com.example.cft_loan.data.entities.*
import com.example.cft_loan.data.local.dao.LoanDao
import com.example.cft_loan.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executors
import javax.inject.Inject

class Repository {
    var sizeOfCall = 5

    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var loanDao: LoanDao

    private val loansConditions: MutableLiveData<List<LoanCondition>> = MutableLiveData()
    private val conditionsArray = ArrayList<LoanCondition>()

    init {
        LoanApp.appComponents.inject(this)
    }

    fun getUserData(): LiveData<User> = loanDao.getUserData()
    fun getLoanList(): LiveData<List<Loan>> = loanDao.getLoans()

    fun getLoansConditions(): MutableLiveData<List<LoanCondition>> = loansConditions

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
                    saveDataToDb(UserBuilder()
                            .setName(userInfo.name)
                            .setPassword(userInfo.password)
                            .setToken(response.body().toString())
                            .build()
                    )

                    getLoanListFromServer(response.body().toString())
                 }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
    }

    fun getLoanListFromServer(token: String) {
        apiService.getLoansList(token).enqueue(object: Callback<LoanList>{
            override fun onResponse(call: Call<LoanList>, response: Response<LoanList>) {
                response.isSuccessful.apply {
                    response.body()?.let {
                        saveLoansToDb(it.loanList)
                    }
                }
            }

            override fun onFailure(call: Call<LoanList>, t: Throwable) {

            }
        })
    }

    fun getLoansConditionsFromServer(token: String) {
        apiService.getLoansConditions(token).enqueue(object: Callback<LoanCondition> {
            override fun onResponse(
                call: Call<LoanCondition>,
                response: Response<LoanCondition>
            ) {
                try {
                    response.body()?.let {
                        conditionsArray.add(it)
                    }
                    sizeOfCall--
                    if (sizeOfCall > 0) getLoansConditionsFromServer(token)
                    else {
                        sizeOfCall = 5
                        loansConditions.value = conditionsArray
                        return
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
              }

            override fun onFailure(call: Call<LoanCondition>, t: Throwable) {

            }
        })
    }


    private fun saveDataToDb(userData: User) {
        Executors.newSingleThreadExecutor().execute(
                fun () {loanDao.saveUserData(userData)}
        )
    }

    private fun saveLoansToDb(loanList: List<Loan>) {
        Executors.newSingleThreadExecutor().execute(
            fun () {loanDao.saveLoans(loanList)}
        )
    }
}