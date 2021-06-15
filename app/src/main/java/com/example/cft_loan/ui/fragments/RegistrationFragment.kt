package com.example.cft_loan.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cft_loan.R
import com.example.cft_loan.errorcheck.CheckLoginAndPassword
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.confirm_button

class RegistrationFragment: Fragment(R.layout.fragment_registration) {
    private val checkInput = CheckLoginAndPassword()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        confirm_button.setOnClickListener {
            val login = registration_name.text.toString()
            val password = registration_password.text.toString()

            if(checkInput.checkLogin(login) && checkInput.checkPassword(password)) {

            }
        }

        if_user_already_registered.setOnClickListener {
            activity?.let {
                it.supportFragmentManager
                     .beginTransaction()
                     .replace(R.id.fragment_container, LoginFragment())
                     .addToBackStack(null)
                     .commit()
            }
        }
    }

}