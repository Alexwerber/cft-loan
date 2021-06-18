package com.example.cft_loan.ui.fragments.loan

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cft_loan.R
import com.example.cft_loan.data.entities.Loan
import com.example.cft_loan.di.constants.BundleKeys.BUNDLE_ID
import com.example.cft_loan.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_loan_info.*
import java.util.concurrent.Executors

class LoanInfoFragment: Fragment(R.layout.fragment_loan_info) {
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loanViewModel = activity?.let { ViewModelProvider(it).get(LoanViewModel::class.java) }!!

    }

    override fun onStart() {
        super.onStart()
        Executors.newSingleThreadExecutor().execute(
            fun () {
                initViews(arguments?.let { loanViewModel.getLoanById(it.getInt(BUNDLE_ID)) }!!)
            }
        )
        activity?.title = resources.getString(R.string.loan_info)
    }

    private fun initViews(let: Loan) {
        loan_info_amount.text = let.amount.toString()
        loan_info_name.text = let.firstName + " " + let.lastName
        loan_info_percent.text = let.percent.toString()
        loan_info_period.text = let.period.toString()
        loan_info_status.text = let.state
    }
}