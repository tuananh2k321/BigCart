package com.example.bigcart.view.main.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.bigcart.R
import com.example.bigcart.databinding.ActivitySearchBinding
import com.example.bigcart.model.Category
import com.example.bigcart.model.Product
import com.example.bigcart.model.User
import com.example.bigcart.view.main.home.adapter.CategoryAdapter
import com.example.bigcart.view.main.home.adapter.HistorySearchAdapter
import com.example.bigcart.view.main.home.adapter.ProductAdapter

class SearchActivity : AppCompatActivity() {
    private var _binding: ActivitySearchBinding? = null
    private val binding: ActivitySearchBinding
        get() = _binding!!

    // history search
    private lateinit var historySearchAdapter: HistorySearchAdapter
    private lateinit var user: User

    // category
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryList: ArrayList<Category>

    // recommended product
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()

       with(binding) {
           // history search
           var searchLayoutManager = GridLayoutManager(applicationContext, 3)
           searchLayoutManager.spanCount = 3
           historySearchAdapter = HistorySearchAdapter(user.getHistorySearch(), applicationContext)
           rcvHistorySearch.adapter = historySearchAdapter

           // category
           var categoryLayoutManager = LinearLayoutManager(applicationContext,
               LinearLayoutManager.HORIZONTAL, false)
           rcvCategory.layoutManager = categoryLayoutManager
           categoryAdapter = CategoryAdapter(applicationContext, categoryList)
           rcvCategory.adapter = categoryAdapter

           // recommended product
           var productLayoutManager = GridLayoutManager(applicationContext, 2)
           productLayoutManager.spanCount = 2
           rcvRecommend.layoutManager = productLayoutManager
           productAdapter = ProductAdapter(applicationContext, productList)
           rcvRecommend.adapter = productAdapter
       }
    }

    private fun initData() {
        user = User(1, "tuananh", "abcxyz", "haizzj123@gmail.com", "0921011337",
                    "01/05/2003", "8:58",  arrayOf("ban phim co", "ban hoc", "giuong ngu em ai", "balo sach vo"))

//        categoryList = arrayListOf<Category>(
//            Category(1, "Vegetables", R.drawable.vegetable, "#E6F2EA"),
//            Category(2, "Fruits", R.drawable.apple, "#FFE9E5"),
//            Category(3, "Beverages", R.drawable.beverage, "#FFF6E3"),
//            Category(4, "Grocery", R.drawable.grocery, "#F3EFFA"),
//            Category(5, "Edible oil", R.drawable.oil, "#DCF4F5"),
//            Category(6, "Household", R.drawable.household, "#FFE8F2")
//        )

        productList = arrayListOf<Product>(
            Product(1, "Peach", "New", "bc", "abc",true, 5.0, 500.0, 1),
            Product(2, "Avocado", "-18%", "bc", "abc",false, 7.0, 500.0, 5),
            Product(3, "Pineapple", "New", "bc", "abc",true, 6.0, 500.0, 18),
            Product(4, "Grapes", "New", "bc", "abc",false, 4.0, 500.0, 1),
            Product(5, "Pomegranates", "New", "bc", "abc",true, 5.0, 500.0, 8),
            Product(6, "Broccoli", "New", "bc", "abc",true, 5.0, 500.0, 1),

            )
    }
}