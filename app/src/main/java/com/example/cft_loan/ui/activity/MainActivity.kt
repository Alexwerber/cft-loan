package com.example.cft_loan.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cft_loan.LoanApp
import com.example.cft_loan.R
import com.example.cft_loan.data.entities.UserInfo
import com.example.cft_loan.data.remote.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoanApp.appComponents.inject(this)


        confirm_button.setOnClickListener {
            Log.i("wwwww", "on click")
            Log.i("wwwww", edit_name.text.toString())
            Log.i("wwwww", edit_password.text.toString())

            registerUser(edit_name.text.toString(), edit_password.text.toString())
            it.setOnClickListener(null)
        }
    }

    private fun registerUser(name: String, password: String) {
        apiService.registerUser(UserInfo(name, password)).enqueue(object: Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                Log.i("wwww", response.body().toString())
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {

            }

        })
    }
}