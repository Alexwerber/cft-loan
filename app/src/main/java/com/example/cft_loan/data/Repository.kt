package com.example.cft_loan.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cft_loan.LoanApp
import com.example.cft_loan.data.entities.*
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

    private var loansConditions: MutableLiveData<MutableList<LoanCondition?>> = MutableLiveData()

    init {
        LoanApp.appComponents.inject(this)
    }

    fun getUserData(): LiveData<User> = loanDao.getUserData()
    fun getLoanList(): LiveData<List<Loan>> = loanDao.getLoans()

    fun getLoansConditions(): MutableLiveData<MutableList<LoanCondition?>> = loansConditions

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
        var id = 0
        val listOfConditions: MutableList<LoanCondition?> = mutableListOf()

        while (id < 10) {
            apiService.getLoansConditions(token).enqueue(object : Callback<LoanCondition> {
                override fun onResponse(
                    call: Call<LoanCondition>,
                    response: Response<LoanCondition>
                ) {
                    listOfConditions.add(
                        index = id,
                        element = response.body())
                }

                override fun onFailure(call: Call<LoanCondition>, t: Throwable) {

                }
            })
            id++
        }

        loansConditions.value = listOfConditions
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