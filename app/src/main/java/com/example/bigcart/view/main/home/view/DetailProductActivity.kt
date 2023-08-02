package com.example.bigcart.view.main.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bigcart.R
import com.example.bigcart.databinding.ActivityDetailProductBinding
import com.example.bigcart.model.Comment
import com.example.bigcart.model.Product
import com.example.bigcart.retrofit.IRetrofit
import com.example.bigcart.retrofit.RetrofitHelper
import com.example.bigcart.view.main.home.adapter.CommentAdapter
import com.example.bigcart.view.main.home.adapter.ProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class DetailProductActivity : AppCompatActivity() {
    private var _binding: ActivityDetailProductBinding? = null
    private val binding: ActivityDetailProductBinding
        get() = _binding!!

    // retrofit
    private lateinit var iRetrofit: IRetrofit

    // get id
    private var idProduct: Int = 0

    // user comment two item
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var commentList: ArrayList<Comment>

    // relevant product list
    private lateinit var productAdapter: ProductAdapter
    private lateinit var relevantProductList: ArrayList<Product>

    // product
    private lateinit var product: Product

    // favorite
    private var isFavorite: Boolean = true

    // more text
    private var isMoreText: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()

        // retrofit
        iRetrofit = RetrofitHelper.createService(IRetrofit::class.java)

        idProduct = intent.getIntExtra("productId", -1)

        with(binding) {
            // back
            imgBack.setOnClickListener {
                finish()
            }

            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val response = iRetrofit.getProductsById(idProduct).execute()
                    Log.e("DetailProduct", "productId: $idProduct")
                    if (response.isSuccessful) {
                        val productRes = response.body()
                        if (productRes != null && productRes.getStatus()) {
                            Log.e("product list", "Response successful")
                            var product: Product = productRes.getProduct()
                            // image product
                            var imageUrl: String = product.getImage()

                            withContext(Dispatchers.Main) {
                                Glide.with(applicationContext).load(imageUrl).fitCenter().into(imgProduct)
                                tvNameProduct.text = product.getName()
                                tvPrice.text ="$ "+product.getPrice().toString()
                                tvWeight.text = "g "+product.getWeight()
                                tvDescription.text = product.getDescription()
                            }
                        }
                    } else {
                        Log.e("product list", "Response unsuccessful")
                    }
                } catch (e: Exception) {
                    Log.e("Exception", "Exception: ",e )
                }
            }


            // add to favorite
            imgFavorite.setOnClickListener{
                if (isFavorite) {
                    imgFavorite.setImageResource(R.drawable.icons8_favorite_24_d)
                    isFavorite = !isFavorite
                } else {
                    imgFavorite.setImageResource(R.drawable.icons8_favorite_24)
                    isFavorite = !isFavorite
                }
            }

            // more text
            tvMoreText.setOnClickListener{
                if (isMoreText) {
                    tvDescription.maxLines = Int.MAX_VALUE
                    tvDescription.ellipsize = null
                    tvMoreText.text = "Less"
                    isMoreText = !isMoreText
                } else {
                    tvDescription.maxLines = 5
                    tvDescription.ellipsize = TextUtils.TruncateAt.END
                    tvMoreText.text = "More"
                    isMoreText = !isMoreText
                }

            }

            // quantity
            tvQuantity.text = "0"
            var quantity: Int = 0

            imgPlus.setOnClickListener{
                quantity += 1
                tvQuantity.text = quantity.toString()
            }

            imgMinus.setOnClickListener{
                quantity -= 1
                tvQuantity.text = quantity.toString()
            }

            // user comment
            var commentLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            rcvComment.layoutManager = commentLayoutManager
            val twoComment = ArrayList(commentList.take(2))
            commentAdapter = CommentAdapter(applicationContext, twoComment)
            rcvComment.adapter = commentAdapter

            // relevant product
            var layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            rcvRelevantProduct.layoutManager = layoutManager
            productAdapter = ProductAdapter(applicationContext, relevantProductList)
            rcvRelevantProduct.adapter = productAdapter

        }
    }

    fun initData() {
        relevantProductList = arrayListOf<Product>(
            Product(1, "Peach", "New", "bc", "abc",true, 5.0, 500.0, 1),
            Product(2, "Avocado", "-18%", "bc", "abc",false, 7.0, 500.0, 5),
            Product(3, "Pineapple", "New", "bc", "abc",true, 6.0, 500.0, 18),
            Product(4, "Grapes", "New", "bc", "abc",false, 4.0, 500.0, 1),
            Product(5, "Pomegranates", "New", "bc", "abc",true, 5.0, 500.0, 8),
            Product(6, "Broccoli", "New", "bc", "abc",true, 5.0, 500.0, 1),

            )

        commentList = arrayListOf<Comment>(
            Comment(1, "lskakksjjajhhshhauusuaksksjduuhsh",
                arrayOf("https://free.vector6.com/wp-content/uploads/2021/05/PNG-0000002409-png-qua-chanh.png",
                    "https://free.vector6.com/wp-content/uploads/2021/05/PNG-0000002409-png-qua-chanh.png"),
                "01/05/2003", 4, 1, 1
            ),
            Comment(2, "lskakksjjajhhshhauusuaksksjduuhsh",
                arrayOf("https://free.vector6.com/wp-content/uploads/2021/05/PNG-0000002409-png-qua-chanh.png",
                    "https://free.vector6.com/wp-content/uploads/2021/05/PNG-0000002409-png-qua-chanh.png"),
                "01/05/2003", 5, 2, 1
            )
        )

        product = Product(1, "Peach", "New", "bc", "abc",true, 5.0, 500.0, 1)
    }

}