package com.example.cft_loan.ui.fragments

import androidx.fragment.app.Fragment
import com.example.cft_loan.R
import com.example.cft_loan.errorcheck.CheckLoginAndPassword
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: Fragment(R.layout.fragment_login) {
    private val checkInput = CheckLoginAndPassword()

    override fun onStart() {
        super.onStart()

        confirm_button.setOnClickListener {
            val login = login_name.text.toString()
            val password = login_password.text.toString()

            if(checkInput.checkLogin(login) && checkInput.checkPassword(password)) {

            }
        }
    }
}