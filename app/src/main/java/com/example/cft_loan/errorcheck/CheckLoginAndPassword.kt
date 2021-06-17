package com.example.cft_loan.errorcheck

class CheckLoginAndPassword {
        public fun checkLogin(str: String): Boolean =
                str.length >= 4

        public fun checkPassword(str: String): Boolean =
                str.contains(" ")
}