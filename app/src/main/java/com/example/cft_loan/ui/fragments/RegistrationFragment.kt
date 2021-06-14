package com.example.cft_loan.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cft_loan.R
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment: Fragment(R.layout.fragment_registration) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

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