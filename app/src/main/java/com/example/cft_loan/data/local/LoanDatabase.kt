package com.example.cft_loan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cft_loan.data.entities.Loan
import com.example.cft_loan.data.local.dao.LoanDao

@Database(entities = arrayOf(Loan::class), version = 1)
abstract class LoanDatabase: RoomDatabase() {
    abstract fun getLoanDao(): LoanDao
}