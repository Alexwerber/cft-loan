package com.example.cft_loan.data.entities

import com.google.gson.annotations.SerializedName

data class LoanCondition(
    @SerializedName("percent") val percent: Double,
    @SerializedName("period") val period: Int,
    @SerializedName("maxAmount") val maxAmount: Long
)
