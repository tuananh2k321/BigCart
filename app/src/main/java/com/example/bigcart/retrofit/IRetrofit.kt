package com.example.bigcart.retrofit

import com.example.bigcart.api_respon.CategoryRes
import com.example.bigcart.api_respon.FavoriteRes
import com.example.bigcart.api_respon.ProductRes
import com.example.bigcart.model.Category
import com.example.bigcart.model.Product
import com.example.bigcart.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IRetrofit {
    // get list product
    @GET("/api/product/get_all_product.php")
    fun getAllProduct(): Call<ProductRes>

    // top product
    @GET("/api/product/get_top_product.php")
    fun getTopProduct(): Call<ProductRes>

    // discount product
    @GET("/api/product/get_discount_product.php")
    fun getDiscountProduct(): Call<ProductRes>

    // new product
    @GET("/api/product/get_new_product.php")
    fun getNewProduct(): Call<ProductRes>

    // category product
    @GET("/api/product/get_category_product.php")
    fun getTypeProducts(@Query("categoryId") categoryId: Int): Call<ProductRes>

    // product by id
    @GET("/api/product/get_product_id.php")
    fun getProductsById(@Query("productId") productId: Int): Call<ProductRes>


    // favorites
    // get favorites with id user
    @GET("/api/favorite/get_favorites.php")
    fun getFavorites(@Query("userId") userId: Int): Call<FavoriteRes>

    // add favorites with id user
    @GET("/api/favorite/add_to_favorite.php")
    fun addToFavorites(@Query("userId") userId: Int, @Query("productId") productId: Int): Call<FavoriteRes>

    // delete favorites by id
    @DELETE("/api/favorite/delete_favorite_by_id.php")
    fun deleteFavoriteById(@Query("favoriteId") favoriteId: Int): Call<FavoriteRes>


    // get list category
    @GET("/api/category/get_all_category.php")
    fun getAllCategory(): Call<CategoryRes>

    // register
    @POST("/api/register.php")
    fun register(@Body user: User): Call<User>

    // login
    @POST("/api/login.php")
    fun login(@Body user: User): Call<User>
}