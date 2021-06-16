package com.example.cft_loan.errorcheck

class CheckLoginAndPassword {
        fun checkLogin(str: String): Boolean =
                str.length >= 4

        fun checkPassword(str: String): Boolean =
                !str.contains(" ")
}