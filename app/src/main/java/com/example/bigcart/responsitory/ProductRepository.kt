package com.example.bigcart.responsitory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bigcart.model.Product
import com.example.bigcart.model.User

class ProductRepository {
    private val productLiveData = MutableLiveData<Product>()

    // Phương thức để lưu trữ thông tin người dùng
    fun updateProduct(product: Product) {
        // Cập nhật dữ liệu chỉ trên luồng chính (main thread)
        productLiveData.postValue(product)
        Log.e("ProductRepository-UpdateProduct", product.toString() )
    }

    // Phương thức để lấy thông tin người dùng
    fun getProduct(): LiveData<Product> {
        return productLiveData
    }

    // Các phương thức khác liên quan đến việc lưu trữ và truy xuất dữ liệu người dùng
    // ...
}