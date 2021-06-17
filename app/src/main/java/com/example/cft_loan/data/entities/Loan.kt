package com.example.cft_loan.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "loan")
data class Loan(
    @PrimaryKey
    @SerializedName("id") var id: Int,

    @SerializedName("amount") var amount: Int,
    @SerializedName("date") var date: String,
    @SerializedName("firstName") var firstName: String,
    @SerializedName("lastName")  var lastName: String,
    @SerializedName("percent") var percent: Double,
    @SerializedName("period") var period: Int,
    @SerializedName("phoneNumber") var phoneNumber: String,
    @SerializedName("state") var state: String
)
