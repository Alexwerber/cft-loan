package com.example.cft_loan.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class Loan(
    @SerializedName("amount") var amount: Int,
    @SerializedName("date") var date: Date,
    @SerializedName("firstName") var firstName: String,
    @SerializedName("id") var id: Int,
    @SerializedName("lastName")  var lastName: String,
    @SerializedName("percent") var percent: Double,
    @SerializedName("period") var period: Int,
    @SerializedName("phoneNumber") var phoneNumber: String,
    @SerializedName("state") var state: String
)
