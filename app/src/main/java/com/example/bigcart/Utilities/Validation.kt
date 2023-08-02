package com.example.bigcart.Utilities

class Validation {
    fun isEmpty(text: String): Boolean{
        return !text.toString().trim().isEmpty()
    }

    fun validateEmail(email: String): Boolean {
        val regexPattern = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+$")
        return regexPattern.matches(email)
    }

    fun validateName(name: String): Boolean {
        val regex = Regex("^[A-Za-z\\s']+\$")
        return regex.matches(name)
    }

    fun validateBirthday(birthday: String): Boolean {
        val regex = Regex("^\\d{2}/\\d{2}/\\d{4}\$")
        return regex.matches(birthday)
    }

    fun validatePassword(password: String): Boolean {
        val regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
        return regex.matches(password)
    }

    fun validateVietnamesePhoneNumber(phoneNumber: String): Boolean {
        val regex = Regex("^(0\\d{9}|\\+84\\d{9})\$")
        return regex.matches(phoneNumber)
    }

    // "dd/MM/yyyy" to "yyyy/MM/dd"
    fun reverseDateFormat(inputDate: String): String {
        val parts = inputDate.split("/")
        if (parts.size != 3) {
            return "Invalid input date format"
        }

        val day = parts[0]
        val month = parts[1]
        val year = parts[2]

        return "$year/$month/$day"
    }
}