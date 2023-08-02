package com.example.bigcart.model

class Address (private var id: Int, private var address: String,
               private var default: Boolean, private var name: String,
               private var phoneNumber: String, private var userId: Int){

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getAddress(): String {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun getDefault(): Boolean {
        return default
    }

    fun setDefault(default: Boolean) {
        this.default = default
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getPhoneNumber(): String {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String) {
        this.phoneNumber = phoneNumber
    }

    fun getUserId(): Int {
        return userId
    }

    fun seUserId(userId: Int) {
        this.userId = userId
    }
}