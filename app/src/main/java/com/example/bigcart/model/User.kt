package com.example.bigcart.model


class User(private var id: Int = 0,
           private var fullName: String = "",
           private var password: String,
           private var email: String,
           private var phoneNumber: String = "",
           private var birthday: String ="",
           private var createAt: String = "",
           private var historySearch: Array<String> = arrayOf(),
           private var address: String = "",
           private var message: String = "",
           private var status: Boolean = false,
           private var avatar: String = "",
           private var isLoggedIn: Boolean = false
           )
{

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getFullName(): String {
        return fullName
    }

    fun setFullName(fullName: String) {
        this.fullName = fullName
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getEmail(): String {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPhoneNumber(): String {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String) {
        this.phoneNumber = phoneNumber
    }

    fun getBirthday(): String {
        return birthday
    }

    fun setBirthday(birthday: String) {
        this.birthday = birthday
    }

    fun getCreateAt(): String {
        return createAt
    }

    fun setCreateAt(createAt: String) {
        this.createAt = createAt
    }

    fun getAddress(): String {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun getHistorySearch(): Array<String> {
        return historySearch
    }

    fun setHistorySearch(historySearch: Array<String>) {
        this.historySearch = historySearch
    }

    fun getStatus(): Boolean {
        return status
    }

    fun setStatus(status: Boolean) {
        this.status = status
    }

    fun getMessage(): String {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getAvatar(): String {
        return avatar
    }

    fun setAvatar(avatar: String) {
        this.avatar = avatar
    }

    fun getIsLoggedIn(): Boolean {
        return isLoggedIn
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        this.isLoggedIn = isLoggedIn
    }

    override fun toString(): String {
        return "User(id=$id, fullName='$fullName', password='$password', email='$email', phoneNumber='$phoneNumber', birthday='$birthday', createAt='$createAt', historySearch=${historySearch.contentToString()}, address='$address', message='$message', status=$status, avatar='$avatar')"
    }


}