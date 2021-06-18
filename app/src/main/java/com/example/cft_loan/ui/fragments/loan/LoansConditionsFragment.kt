package com.example.cft_loan.ui.fragments.loan

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cft_loan.R
import com.example.cft_loan.data.entities.LoanCondition
import com.example.cft_loan.di.constants.BundleKeys.MAX_AMOUNT
import com.example.cft_loan.di.constants.BundleKeys.PERCENT
import com.example.cft_loan.di.constants.BundleKeys.PERIOD
import com.example.cft_loan.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_loans_conditions.*

class LoansConditionsFragment: Fragment(R.layout.fragment_loans_conditions) {
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loanViewModel = activity?.let { ViewModelProvider(it).get(LoanViewModel::class.java) }!!
    }

    override fun onStart() {
        super.onStart()

        activity?.title = resources.getString(R.string.conditions_title)

        loanViewModel.getLoansConditions().observe(requireActivity(), {
            setLoanCondition(it)
        })

        go_to_create_loan.setOnClickListener() {
            goToCreateNewLoan()
        }

        next_loan_condition.setOnClickListener() {
            loanViewModel.getToken()?.let { loanViewModel.loadLoanConditionsFromServer(it) }
        }
    }

    private fun goToCreateNewLoan() {
        activity?.let {
            val bundle = loanViewModel.getLoansConditions().value?.let {
                fillDataToCreateNewLoan(it)
            }

            val createLoanFragment = CreateLoanFragment()
            createLoanFragment.arguments = bundle

            it.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, createLoanFragment)
                    .addToBackStack(null)
                    .commit() }
    }

    private fun setLoanCondition(loanCondition: LoanCondition) {
        condition_period.text = loanCondition.period.toString()
        condition_max_amount.text = loanCondition.maxAmount.toString()
        condition_percent.text = loanCondition.percent.toString()
    }

    private fun fillDataToCreateNewLoan(value: LoanCondition): Bundle = Bundle().apply {
        putInt(PERIOD, value.period)
        putLong(MAX_AMOUNT, value.maxAmount)
        putDouble(PERCENT, value.percent)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        loanViewModel.getLoansConditions().removeObservers(requireActivity())
    }
}