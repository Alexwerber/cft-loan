package com.example.cft_loan.ui.fragments.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cft_loan.R
import com.example.cft_loan.data.entities.UserInfo
import com.example.cft_loan.errorcheck.CheckLoginAndPassword
import com.example.cft_loan.ui.fragments.loan.LoansFragment
import com.example.cft_loan.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.confirm_button

class RegistrationFragment: Fragment(R.layout.fragment_registration) {
    private val checkInput = CheckLoginAndPassword()
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loanViewModel = activity?.let { ViewModelProvider(it).get(LoanViewModel::class.java) }!!

        loanViewModel.getToken()?.let { goToLoansFragment() }
    }

    private fun goToLoansFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, LoansFragment())
            .commit()
    }

    override fun onStart() {
        super.onStart()

        confirm_button.setOnClickListener {
            val login = registration_name.text.toString()
            val password = registration_password.text.toString()

            if(checkInput.checkLogin(login) && checkInput.checkPassword(password)) {
                val userInfo = UserInfo(login, password)
                loanViewModel.registerUser(userInfo)
            } else {
                registration_name.error = resources.getString(R.string.login_error)
                registration_password.error = resources.getString(R.string.password_error)
            }
        }

        if_user_already_registered.setOnClickListener {
            activity?.let {
                it.supportFragmentManager
                     .beginTransaction()
                     .replace(R.id.fragment_container, LoginFragment())
                     .commit()
            }
        }
    }

}