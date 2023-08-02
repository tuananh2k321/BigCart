package com.example.bigcart.api_respon

import com.example.bigcart.model.Product

class FavoriteRes(
    private var message: String,
    private var status: Boolean,
    private var favorites: ArrayList<Product>
) {
    fun getMessage(): String{
        return message
    }

    fun getStatus(): Boolean{
        return status
    }

    fun getFavorites(): ArrayList<Product>{
        return favorites
    }

    override fun toString(): String {
        return "FavoriteRes(message='$message', status=$status, favorites=$favorites)"
    }

}