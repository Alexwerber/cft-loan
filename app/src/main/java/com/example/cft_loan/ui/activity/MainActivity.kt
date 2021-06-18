package com.example.cft_loan.ui.activity

import android.os.Bundle
import android.util.Log
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

        loanViewModel.checkWhenTokenChange().observe(this, {
            if (it != null && loanViewModel.firstLaunch) {
                loanViewModel.firstLaunch = false
                Log.i("wwww", "wwwww")
                goToLoansFragment()
            }
        })
        }

    private fun goToLoansFragment() {
        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, LoansFragment())
            .commit()
    }
}