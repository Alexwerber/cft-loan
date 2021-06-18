package com.example.cft_loan.di

class StringConverter {
    companion object {
        fun compareString(string: String, secondString: String): String =
            "$string $secondString"
    }
}