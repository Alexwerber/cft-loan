package com.example.cft_loan.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
        @PrimaryKey
        val id: Int = 1,

        var phoneNumber: String,
        var name: String,
        var password: String,
        var firstName: String,
        var lastName: String,
        var token: String
)
