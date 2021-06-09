package com.example.cft_loan.di.components

import android.app.Application
import com.example.cft_loan.di.modules.RetrofitModule
import com.example.cft_loan.ui.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RetrofitModule::class
])
interface AppComponents {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponents?
    }

    fun inject(mainActivity: MainActivity)
}