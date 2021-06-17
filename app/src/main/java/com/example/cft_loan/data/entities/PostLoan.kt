package com.example.cft_loan.data.entities

data class User(
        var amount: Long?,
        var firstName: String?,
        var lastName: String?,
        var period: Int?,
        var percent: Double?,
        var phoneNumber: String?,
)

class PostLoanBuilder() {
        private var amount: Long? = null
        private var firstName: String? = null
        private var lastName: String? = null
        private var period: Int? = null
        private var percent: Double? = null
        private var phoneNumber: String? = null


        fun setAmount(value: Long): PostLoanBuilder = this.apply { amount = value }
        fun setFirsName(value: String): PostLoanBuilder = this.apply { firstName = value }
        fun setLastName(value: String): PostLoanBuilder = this.apply { lastName = value }
        fun setPeriod(value: Int): PostLoanBuilder = this.apply { period = value }
        fun setPercent(value: Double): PostLoanBuilder = this.apply { percent = value }
        fun setPhoneNumber(value: String): PostLoanBuilder = this.apply { phoneNumber = value }

        fun build() =
                User(
                        amount = amount,
                        phoneNumber = phoneNumber,
                        firstName = firstName,
                        lastName = lastName,
                        percent = percent,
                        period = period
                )
}
