package com.example.cft_loan.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cft_loan.R
import com.example.cft_loan.ui.adapters.LoansListAdapter
import com.example.cft_loan.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_loan_list.*

class LoansFragment: Fragment(R.layout.fragment_loan_list) {
    private lateinit var loanViewModel: LoanViewModel
    private val adapter = LoansListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loanViewModel = activity?.let { ViewModelProvider(it).get(LoanViewModel::class.java) }!!
        loanViewModel.getToken()?.let { loanViewModel.loadLoanListFromSerer(it) }
    }

    override fun onStart() {
        super.onStart()

        loans_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@LoansFragment.adapter
        }

        loanViewModel.getLoanList().observe(requireActivity(), {
            adapter.setData(it)
        })

        go_to_loans_conditions.setOnClickListener() {
            loanViewModel.getToken()?.let { loanViewModel.loadLoanConditionsFromServer(it) }

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, LoansConditionsFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}