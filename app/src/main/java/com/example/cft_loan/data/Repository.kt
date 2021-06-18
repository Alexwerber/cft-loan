package com.example.cft_loan.data

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cft_loan.LoanApp
import com.example.cft_loan.data.entities.*
import com.example.cft_loan.data.local.dao.LoanDao
import com.example.cft_loan.data.remote.ApiService
import com.example.cft_loan.di.constants.SharedPreferencesKeys.PREF_NAME
import com.example.cft_loan.di.constants.SharedPreferencesKeys.TOKEN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executors
import javax.inject.Inject

class Repository {
    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var loanDao: LoanDao
    @Inject
    lateinit var context: Context

    private val loansConditions: MutableLiveData<LoanCondition> = MutableLiveData()
    private var token: MutableLiveData<String> = MutableLiveData()
    private var preferences: SharedPreferences

    init {
        LoanApp.appComponents.inject(this)
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getLoanList(): LiveData<List<Loan>> = loanDao.getLoans()
    fun getToken(): String? = preferences.getString(TOKEN, null)
    fun getLoansConditions(): MutableLiveData<LoanCondition> = loansConditions

    fun checkWhenTokenChange(): MutableLiveData<String> = token

    fun registerUser(userInfo: UserInfo) {
        apiService.registerUser(userInfo).enqueue(object : Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                response.isSuccessful.apply { loginUser(userInfo) }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {

            }
        })
    }

    fun loginUser(userInfo: UserInfo) {
        apiService.loginUser(userInfo).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                response.isSuccessful.apply {
                    preferences.edit().putString(TOKEN, response.body()).apply()
                    token.value = response.body()

                    getLoanListFromServer(response.body().toString())
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
    }

    fun getLoanListFromServer(token: String) {
        apiService.getLoansList(token).enqueue(object : Callback<List<Loan>> {
            override fun onResponse(call: Call<List<Loan>>, response: Response<List<Loan>>) {
                response.isSuccessful.apply {
                    response.body()?.let {
                        saveLoansToDb(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Loan>>, t: Throwable) {

            }
        })
    }

    fun getLoansConditionsFromServer(token: String) {
        apiService.getLoansConditions(token).enqueue(object : Callback<LoanCondition> {
            override fun onResponse(
                call: Call<LoanCondition>,
                response: Response<LoanCondition>
            ) {
                response.body()?.let {
                    loansConditions.value = it
                }
            }

            override fun onFailure(call: Call<LoanCondition>, t: Throwable) {

            }
        })
    }

    fun postLoan(token: String, loan: PostLoan) {
        apiService.postLoan(token, loan).enqueue(object: Callback<Loan> {
            override fun onResponse(call: Call<Loan>, response: Response<Loan>) {

            }

            override fun onFailure(call: Call<Loan>, t: Throwable) {

            }

        })
    }


    private fun saveLoansToDb(loanList: List<Loan>) {
        Executors.newSingleThreadExecutor().execute(
            fun() { loanDao.saveLoans(loanList) }
        )
    }
}