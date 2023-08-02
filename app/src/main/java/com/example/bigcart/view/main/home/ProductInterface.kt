package com.example.bigcart.view.main.home

interface ProductInterface {
    fun onCategoryClick(itemId: Int, name: String)

    fun getProductById(itemId: Int)

    fun addToFavorite(itemId: Int)


}