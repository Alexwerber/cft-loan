package com.example.cft_loan.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cft_loan.data.Repository
import com.example.cft_loan.data.entities.UserInfo

class LoanViewModel: ViewModel() {
    private val repository = Repository()

    fun registerUser(userInfo: UserInfo) {
        repository.registerUser(userInfo)
    }

    fun loginUser(userInfo: UserInfo) {
        repository.loginUser(userInfo)
    }
}