package com.example.bigcart.model

class Product (private var id: Int = 0,
               private var name: String,
               private var statusProduct: String,
               private var image: String,
               private var description: String = "",
               private var favorite: Boolean,
               private var price: Double,
               private var weight: Double,
               private var purchaseQuantity: Long,
               private var quantity: Int = 0,
               private var status: Boolean = false,
               private var message: String = "",
               private var idCategory: Int = 0,
) {

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getDescription(): String {
        return description
    }

    fun setDescription(name: String) {
        this.name = name
    }

    fun getStatusProduct(): String {
        return statusProduct
    }

    fun setStatusProduct(statusProduct: String) {
        this.statusProduct = statusProduct
    }

    fun getImage(): String {
        return image
    }

    fun setImage(image: String) {
        this.image = image
    }

    fun getFavorite(): Boolean {
        return favorite
    }

    fun setFavorite(favorite: Boolean) {
        this.favorite = favorite
    }

    fun getPrice(): Double {
        return price
    }

    fun setPrice(name: Double) {
        this.price = price
    }

    fun getWeight(): Double {
        return weight
    }

    fun setWeight(weight: Double) {
        this.weight = weight
    }

    fun getPurchaseQuantity(): Long {
        return purchaseQuantity
    }

    fun setPurchaseQuantity(purchaseQuantity: Long) {
        this.purchaseQuantity = purchaseQuantity
    }

    fun getQuantity(): Int {
        return quantity
    }

    fun setQuantity(quantity: Int) {
        this.quantity = quantity
    }

    fun getStatus(): Boolean {
        return status
    }

    fun getMessage(): String {
        return message
    }

    fun getIdCategory(): Int {
        return idCategory
    }
}