package com.example.bigcart.api_respon

import com.example.bigcart.model.Category

class CategoryRes(private var message: String,
                  private var status: Boolean,
                  private var categories: ArrayList<Category>
                  ) {
    fun getMessage(): String{
        return message
    }

    fun getStatus(): Boolean{
        return status
    }

    fun getCategories(): ArrayList<Category>{
        return categories
    }

    override fun toString(): String {
        return "CategoryRes(message='$message', status=$status, listCategory=$categories)"
    }
}