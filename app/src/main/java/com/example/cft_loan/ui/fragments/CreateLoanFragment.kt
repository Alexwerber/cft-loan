package com.example.cft_loan.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cft_loan.R
import com.example.cft_loan.di.constants.BundleKeys.MAX_AMOUNT
import com.example.cft_loan.di.constants.BundleKeys.PERCENT
import com.example.cft_loan.di.constants.BundleKeys.PERIOD
import com.example.cft_loan.errorcheck.CheckLoanInput
import com.example.cft_loan.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_create_loan.*

class CreateLoanFragment: Fragment(R.layout.fragment_create_loan) {
    private lateinit var loanViewModel: LoanViewModel
    private var checkLoanInput = CheckLoanInput()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loanViewModel = activity?.let { ViewModelProvider(it).get(LoanViewModel::class.java) }!!
    }

    override fun onStart() {
        super.onStart()

        loan_percent.text = arguments?.getDouble(PERCENT).toString()
        loan_period.text = arguments?.getInt(PERIOD).toString()
        loan_max_amount.text = arguments?.getLong(MAX_AMOUNT).toString()

        post_loan_button.setOnClickListener() {
            val firstName = loan_first_name.text.toString()
            val lastName = loan_last_name.text.toString()
            val phoneNumber = loan_phone_number.text.toString()
            val amount = loan_amount.text.toString()

            if (checkInput(firstName, lastName, phoneNumber) && amount.toLong() < arguments?.getLong(MAX_AMOUNT)!!)
                loanViewModel.getToken()?.let {
                    //val user = UserBuilder().setFirsName().setLastName().setPhoneNumber()
                    //loanViewModel.postLoan(it, )
                }
        }
    }

    private fun checkInput(firstName: String, lastName: String, phoneNumber: String): Boolean =
        checkLoanInput.checkName(firstName) &&
        checkLoanInput.checkName(lastName) &&
        checkLoanInput.checkPhoneNumber(phoneNumber)
}