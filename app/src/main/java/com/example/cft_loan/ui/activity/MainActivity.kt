package com.example.cft_loan.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cft_loan.LoanApp
import com.example.cft_loan.R
import com.example.cft_loan.data.entities.UserInfo
import com.example.cft_loan.data.remote.ApiService
import com.example.cft_loan.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)

        loanViewModel.userData.observe(this, {
            it?.let { if(it.token != "") goToLoanList() }
        })
    }

    private fun goToLoanList() {
        // go to loan fragment
    }
}