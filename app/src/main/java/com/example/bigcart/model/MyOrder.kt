package com.example.bigcart.model

class MyOrder(private var id: Int, private var orderPlace:Boolean, private var orderConfirm:Boolean,
              private var orderShipped:Boolean, private var success:Boolean, private var orderPlaceDate:String,
              private var orderConfirmDate:String, private var orderShippedDate:String, private var successDate:String,
              private var productId: Int)
{

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getOrderPlace(): Boolean {
        return orderPlace
    }

    fun setOrderPlace(orderPlace: Boolean) {
        this.orderPlace = orderPlace
    }

    fun getOrderConfirm(): Boolean {
        return orderConfirm
    }

    fun setOrderConfirm(orderConfirm: Boolean) {
        this.orderConfirm = orderConfirm
    }

    fun getOrderShipped(): Boolean {
        return orderShipped
    }

    fun setOrderShipped(orderShipped: Boolean) {
        this.orderShipped = orderShipped
    }

    fun getSuccess(): Boolean {
        return success
    }

    fun setSuccess(success: Boolean) {
        this.success = success
    }

    fun getOrderPlaceDate(): String {
        return orderPlaceDate
    }

    fun setOrderPlaceDate(orderPlaceDate: String) {
        this.orderPlaceDate = orderPlaceDate
    }

    fun getOrderConfirmDate(): String {
        return orderConfirmDate
    }

    fun setOrderConfirmDate(orderConfirm: Boolean) {
        this.orderConfirm = orderConfirm
    }

    fun getOrderShippedDate(): String {
        return orderShippedDate
    }

    fun setOrderShippedDate(orderShippedDate: String) {
        this.orderShippedDate = orderShippedDate
    }

    fun getSuccessDate(): String {
        return successDate
    }

    fun setSuccessDate(successDate: String) {
        this.successDate = successDate
    }

    fun getProductId(): Int {
        return productId
    }

    fun setProductId(productId: Int) {
        this.productId = productId
    }
}