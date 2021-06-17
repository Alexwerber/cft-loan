package com.example.cft_loan.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cft_loan.R
import com.example.cft_loan.ui.fragments.LoansFragment
import com.example.cft_loan.viewmodel.LoanViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)

        loanViewModel.userData.observe(this, {
            it?.let { if(it.token != "") {
                    goToLoanList()
                    loanViewModel.userData.removeObservers(this)
                }
            }
        })
    }

    private fun goToLoanList() {
        this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, LoansFragment())
                .commit()
    }
}