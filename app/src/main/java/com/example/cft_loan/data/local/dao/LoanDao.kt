package com.example.cft_loan.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cft_loan.data.entities.User

@Dao
interface LoanDao {
    @Query("SELECT * FROM user WHERE id = 1")
    fun getUserData(): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveUserData(user: User)
}