package com.example.cft_loan.data.local

import androidx.room.Database
import com.example.cft_loan.data.entities.User
import com.example.cft_loan.data.local.dao.LoanDao

@Database(entities = [User::class], version = 1)
abstract class LoanDatabase {
    abstract fun getLoanDao(): LoanDao
}