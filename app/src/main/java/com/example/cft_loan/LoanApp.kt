package com.example.cft_loan

import android.app.Application
import com.example.cft_loan.di.components.AppComponents
import com.example.cft_loan.di.components.DaggerAppComponents

class LoanApp: Application() {
    companion object {
        lateinit var appComponents: AppComponents
    }

    override fun onCreate() {
        super.onCreate()

        appComponents = DaggerAppComponents.builder().application(this).build()

    }
}