package com.example.bigcart.model

class Category(private var id: Int, private var name: String, private var image: String, private var bgColor: String) {
    fun getId(): Int{
        return id
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String{
        return name
    }

    fun setImage(image: String){
        this.image = image
    }

    fun getImage(): String{
        return image
    }

    fun setBgColor(bgColor: String){
        this.bgColor = bgColor
    }

    fun getBgColor(): String {
        return bgColor
    }

    override fun toString(): String {
        return "Category(id=$id, name='$name', image=$image, bgColor='$bgColor')"
    }

}