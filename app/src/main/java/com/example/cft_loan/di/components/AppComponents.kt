package com.example.cft_loan.di.components

import android.app.Application
import com.example.cft_loan.data.Repository
import com.example.cft_loan.di.modules.DatabaseModule
import com.example.cft_loan.di.modules.RetrofitModule
import com.example.cft_loan.ui.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RetrofitModule::class,
    DatabaseModule::class
])
interface AppComponents {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponents?
    }

    fun inject(repository: Repository)
}