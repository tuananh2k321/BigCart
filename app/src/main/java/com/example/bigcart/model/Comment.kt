package com.example.bigcart.model

class Comment(private var id: Int, private var content: String, private var listImg: Array<String>,
                private var createAt: String, private var rating: Int,
              private var userId: Int, private var productId: Int)
{
    fun getId(): Int {
        return id
    }

    fun getUserId(): Int {
        return userId
    }

    fun getProductId(): Int {
        return productId
    }

    fun getContent(): String {
        return content
    }

    fun setContent(content: String){
        this.content = content
    }

    fun getListImage(): Array<String> {
        return listImg
    }

    fun setListImage(listImg: Array<String>){
        this.listImg = listImg
    }

    fun getCreateAt(): String {
        return createAt
    }

    fun setCreateAt(createAt: String){
        this.createAt = createAt
    }

    fun getRating(): Int {
        return rating
    }

    fun setRating(rating: Int){
        this.rating = rating
    }
}