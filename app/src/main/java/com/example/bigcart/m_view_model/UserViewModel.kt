package com.example.bigcart.m_view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.bigcart.model.User
import com.example.bigcart.responsitory.UserRepository

class UserViewModel: ViewModel() {
    private val userRepository = UserRepository() // Thay thế UserRepository bằng lớp xử lý lưu trữ dữ liệu user

    private val userLiveData = MutableLiveData<User>()


    // Các phương thức để cập nhật và truy xuất dữ liệu
    fun updateUser(user: User) {
        Log.e("UserViewModel", user.toString() )
        Log.e("UserViewModel", userLiveData.toString() )
        userRepository.updateUser(user)
        userLiveData.value = user
        Log.e("userLiveData", userLiveData.value?.toString() ?: "null")
    }


    fun getUserLiveData(): MutableLiveData<User> {
        return userLiveData
    }
}