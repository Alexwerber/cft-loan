package com.example.cft_loan.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cft_loan.R
import com.example.cft_loan.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_loan_list.*

class LoansFragment: Fragment(R.layout.fragment_loan_list) {
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loanViewModel = activity?.let { ViewModelProvider(it).get(LoanViewModel::class.java) }!!
    }

    override fun onStart() {
        super.onStart()
        go_to_loans_conditions.setOnClickListener() {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, LoansConditionsFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}