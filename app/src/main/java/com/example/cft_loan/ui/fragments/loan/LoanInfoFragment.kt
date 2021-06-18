package com.example.cft_loan.ui.fragments.loan

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cft_loan.R
import com.example.cft_loan.viewmodel.LoanViewModel

class LoanInfoFragment: Fragment(R.layout.fragment_loan_info) {
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loanViewModel = activity?.let { ViewModelProvider(it).get(LoanViewModel::class.java) }!!


    }

    override fun onStart() {
        super.onStart()

        activity?.title = resources.getString(R.string.loan_info)
    }
}