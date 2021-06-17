package com.example.cft_loan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cft_loan.data.Repository
import com.example.cft_loan.data.entities.User
import com.example.cft_loan.data.entities.UserInfo

class LoanViewModel: ViewModel() {
    private val repository = Repository()
    var userData: LiveData<User> = repository.getUserData()

    fun registerUser(userInfo: UserInfo) {
        repository.registerUser(userInfo)
    }

    fun loginUser(userInfo: UserInfo) {
        repository.loginUser(userInfo)
    }

    fun getGetLoanConditionsFromServer(token: String) {
        repository.getLoansConditionsFromServer(token)
    }

    fun getLoanList() =
        repository.getLoanList()

    fun getLoansConditions() =
        repository.getLoansConditions()
}