package com.example.cft_loan.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cft_loan.R
import com.example.cft_loan.ui.adapters.LoansConditionsAdapter
import com.example.cft_loan.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_loan_list.*
import kotlinx.android.synthetic.main.fragment_loans_conditions.*

class LoansConditionsFragment: Fragment(R.layout.fragment_loans_conditions) {
    private lateinit var loanViewModel: LoanViewModel
    private val adapter = LoansConditionsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loanViewModel = activity?.let { ViewModelProvider(it).get(LoanViewModel::class.java) }!!

        loanViewModel.getToken()?.let { loanViewModel.loadGetLoanConditionsFromServer(it) }
    }

    override fun onStart() {
        super.onStart()

        loans_conditions_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@LoansConditionsFragment.adapter
        }

        loanViewModel.getLoansConditions().observe(requireActivity(), {
            adapter.setData(it)
        })
    }
}