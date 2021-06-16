package com.example.cft_loan.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
        @PrimaryKey
        val id: Int = 1,

        var phoneNumber: String?,
        var name: String?,
        var password: String?,
        var firstName: String?,
        var lastName: String?,
        var token: String? = ""
)

class UserBuilder() {
        private var phoneNumber: String? = null
        private var name: String? = null
        private var password: String? = null
        private var firstName: String? = null
        private var lastName: String? = null
        private var token: String = ""

        fun setPhoneNumber(value: String): UserBuilder = this.apply { phoneNumber = value }
        fun setName(value: String): UserBuilder = this.apply { name = value }
        fun setPassword(value: String): UserBuilder = this.apply { password = value }
        fun setFirsName(value: String): UserBuilder = this.apply { firstName = value }
        fun setLastName(value: String): UserBuilder = this.apply { lastName = value }
        fun setToken(value: String): UserBuilder = this.apply { token = value }

        fun build() =
                User(
                        phoneNumber = phoneNumber,
                        name = name,
                        password = password,
                        firstName = firstName,
                        lastName = lastName,
                        token = token
                )
}
