package com.example.cft_loan.errorcheck

class CheckLoanInput {
    fun checkName(str: String): Boolean =
        str.isNotEmpty()

    fun checkPhoneNumber(str: String): Boolean =
        str.isNotEmpty() && str.length <= 12
}