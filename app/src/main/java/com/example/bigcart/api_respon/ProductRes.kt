package com.example.bigcart.api_respon

import com.example.bigcart.model.Category
import com.example.bigcart.model.Product

class ProductRes(private var message: String,
                 private var status: Boolean,
                 private var product: Product,
                 private var products: ArrayList<Product>
) {
    fun getMessage(): String{
        return message
    }

    fun getStatus(): Boolean{
        return status
    }

    fun getProduct(): Product{
        return product
    }

    fun getProducts(): ArrayList<Product>{
        return products
    }

    override fun toString(): String {
        return "ProductRes(message='$message', status=$status, product=$product, products=$products)"
    }
}