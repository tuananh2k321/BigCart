package com.example.bigcart.view.main.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bigcart.databinding.ActivityListProductBinding
import com.example.bigcart.model.Product
import com.example.bigcart.view.main.home.adapter.ProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.bigcart.retrofit.IRetrofit
import com.example.bigcart.retrofit.RetrofitHelper
import com.example.bigcart.view.main.home.ProductInterface


class ListProductActivity : AppCompatActivity(){
    private var _binding: ActivityListProductBinding? = null
    private val binding: ActivityListProductBinding
        get() = _binding!!

    // retrofit
    private lateinit var iRetrofit: IRetrofit

    private var categoryId: Int = 0
    private var categoryName: String = ""
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // retrofit
        iRetrofit = RetrofitHelper.createService(IRetrofit::class.java)

        // get category id
        categoryId = intent.getIntExtra("categoryId", -1)
        categoryName = intent.getStringExtra("categoryName").toString()


        with(binding) {
            // back
            imgBack.setOnClickListener{
                finish()
            }
            // recycle view product
            var layoutManager = GridLayoutManager(applicationContext, 2)
            layoutManager.spanCount = 2
            rcvProduct.layoutManager = layoutManager

            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val response = iRetrofit.getTypeProducts(categoryId).execute()
                    Log.e("ListProduct categoryId:", categoryId.toString())
                    if (response.isSuccessful) {
                        val productRes = response.body()
                        if (productRes != null && productRes.getStatus()) {
                            Log.e("product list", "Response successful")
                            Log.e("product list", productRes.toString())
                            productList = productRes.getProducts()

                            withContext(Dispatchers.Main) {
                                // Xử lý UI trên Main thread
                                productAdapter = ProductAdapter(applicationContext, productList)
                                binding.rcvProduct.adapter = productAdapter
                                tvTitle.text = categoryName
                            }
                        } else {
                            Log.e("product list", "Response unsuccessful")
                        }
                    } else {
                        // Xử lý lỗi khi gọi API lấy danh sách category
                        Log.e("product list", "Response not successful: ${response.code()}")
                    }
                } catch (e: Exception) {
                    // Xử lý lỗi khi gọi API
                    Log.e("top sale product", "onFailure: ${e.message}")
                }
            }
        }
    }

}


