package com.example.cft_loan.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cft_loan.data.Repository
import com.example.cft_loan.data.entities.UserInfo

class LoanViewModel: ViewModel() {
    private val repository = Repository()
    var firstLaunch = true

    fun getToken(): String? = repository.getToken()

    fun checkWhenTokenChange(): LiveData<String> = repository.checkWhenTokenChange()

    fun registerUser(userInfo: UserInfo) {
        repository.registerUser(userInfo)
    }

    fun loginUser(userInfo: UserInfo) {
        repository.loginUser(userInfo)
    }

    fun loadGetLoanConditionsFromServer(token: String) {
        repository.getLoansConditionsFromServer(token)
    }

    fun getLoanList() = repository.getLoanList()

    fun getLoansConditions() = repository.getLoansConditions()
}