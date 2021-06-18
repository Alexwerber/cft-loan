package com.example.cft_loan.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cft_loan.data.entities.Loan

@Dao
interface LoanDao {
    @Query("SELECT * FROM loan")
    fun getLoans(): LiveData<List<Loan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLoans(loansList: List<Loan>)

    @Query("SELECT * FROM loan WHERE id=:id")
    fun getLoanById(id: Int): Loan
}