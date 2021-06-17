package com.example.cft_loan.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cft_loan.R
import com.example.cft_loan.data.entities.UserInfo
import com.example.cft_loan.errorcheck.CheckLoginAndPassword
import com.example.cft_loan.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: Fragment(R.layout.fragment_login) {
    private val checkInput = CheckLoginAndPassword()
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loanViewModel = activity?.let { ViewModelProvider(it).get(LoanViewModel::class.java) }!!
    }

    override fun onStart() {
        super.onStart()

        confirm_button.setOnClickListener {
            val login = login_name.text.toString()
            val password = login_password.text.toString()

            if(checkInput.checkLogin(login) && checkInput.checkPassword(password)) {
                val userInfo = UserInfo(login, password)
                loanViewModel.loginUser(userInfo)
                // when get token go to loan list
            }
        }
    }
}