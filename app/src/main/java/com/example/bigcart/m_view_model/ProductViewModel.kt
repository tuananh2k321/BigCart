package com.example.bigcart.m_view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bigcart.model.Product
import com.example.bigcart.model.User
import com.example.bigcart.responsitory.ProductRepository


class ProductViewModel: ViewModel() {
    private val productRepository = ProductRepository()

    private val productLiveData = MutableLiveData<Product>()


    // Các phương thức để cập nhật và truy xuất dữ liệu
    fun updateUser(product: Product) {
        Log.e("ProductViewModel", product.toString() )
        Log.e("ProductViewModel", productLiveData.toString() )
        productRepository.updateProduct(product)
        productLiveData.postValue(product)
        Log.e("productLiveData", productLiveData.value?.toString() ?: "null")
    }


    fun getUserLiveData(): LiveData<Product> {
        return productLiveData
    }
}