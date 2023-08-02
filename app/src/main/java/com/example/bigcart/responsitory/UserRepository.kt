package com.example.bigcart.responsitory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bigcart.model.User

class UserRepository {
    private val userLiveData = MutableLiveData<User>()

    // Phương thức để lưu trữ thông tin người dùng
    fun updateUser(user: User) {
        // Cập nhật dữ liệu chỉ trên luồng chính (main thread)
        userLiveData.postValue(user)
        Log.e("UserRepository", user.toString() )
    }

    // Phương thức để lấy thông tin người dùng
    fun getUser(): LiveData<User> {
        return userLiveData
    }

    // Phương thức để kiểm tra xem người dùng đã đăng nhập hay chưa
    fun isLoggedIn(): Boolean {
        return userLiveData.value?.getIsLoggedIn() ?: false
    }

    // Các phương thức khác liên quan đến việc lưu trữ và truy xuất dữ liệu người dùng
    // ...
}
