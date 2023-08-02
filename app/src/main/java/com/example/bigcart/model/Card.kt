package com.example.bigcart.model

class Card(private var id: Int, private var name:String, private var cardNumber: String,
           private var expireDate: String, private var ccv:String, private var default: Boolean,) {
    // Getter và Setter cho thuộc tính id
    fun getId(): Int {
        return id
    }

    fun setId(newId: Int) {
        id = newId
    }

    // Getter và Setter cho thuộc tính name
    fun getName(): String {
        return name
    }

    fun setName(newName: String) {
        name = newName
    }

    // Getter và Setter cho thuộc tính cardNumber
    fun getCardNumber(): String {
        return cardNumber
    }

    fun setCardNumber(newCardNumber: String) {
        cardNumber = newCardNumber
    }

    // Getter và Setter cho thuộc tính expireDate
    fun getExpireDate(): String {
        return expireDate
    }

    fun setExpireDate(newExpireDate: String) {
        expireDate = newExpireDate
    }

    // Getter và Setter cho thuộc tính ccv
    fun getCcv(): String {
        return ccv
    }

    fun setCcv(newCcv: String) {
        ccv = newCcv
    }

    // Getter và Setter cho thuộc tính default
    fun getDefault(): Boolean {
        return default
    }

    fun setDefault(default: Boolean) {
        this.default = default
    }
}