package com.example.cft_loan.di.modules

import android.app.Application
import androidx.room.Room
import com.example.cft_loan.data.local.LoanDatabase
import com.example.cft_loan.data.local.dao.LoanDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideLoanDatabase(application: Application): LoanDatabase =
            Room.databaseBuilder(application, LoanDatabase::class.java, "loan.db").build()

    @Singleton
    @Provides
    fun provideLoanDao(loanDatabase: LoanDatabase): LoanDao =
            loanDatabase.getLoanDao()
}