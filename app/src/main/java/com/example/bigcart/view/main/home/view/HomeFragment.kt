package com.example.bigcart.view.main.home.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigcart.R
import com.example.bigcart.model.Category
import com.example.bigcart.model.Product
import com.example.bigcart.retrofit.IRetrofit
import com.example.bigcart.retrofit.RetrofitHelper
import com.example.bigcart.view.main.favorite.FavoriteAdapter
import com.example.bigcart.view.main.home.ProductInterface
import com.example.bigcart.view.main.home.adapter.CategoryAdapter
import com.example.bigcart.view.main.home.adapter.ProductAdapter
import com.example.bigcart.view.main.home.adapter.SliderAdapter
import com.example.bigcart.view.main.home.adapter.TopProductAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment(), ProductInterface {
    // sharedPrefer
    private lateinit var sharedPrefs: SharedPreferences
    private var userId: Int = -1

    // retrofit
    private lateinit var iRetrofit: IRetrofit

    // slider
    lateinit var imageUrl: ArrayList<String>
    lateinit var sliderView: SliderView
    lateinit var sliderAdapter: SliderAdapter

    // category
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var rcvCategory: RecyclerView
    lateinit var categoryList: ArrayList<Category>

    // product
    private lateinit var discountProductAdapter: ProductAdapter
    lateinit var newProductAdapter: ProductAdapter
    private lateinit var productAdapterTopSale: TopProductAdapter
    private lateinit var rcvDiscountProduct: RecyclerView
    lateinit var rcvNewProduct: RecyclerView
    lateinit var rcvProductTopSale: RecyclerView
    lateinit var discountProductList: ArrayList<Product>
    lateinit var newProductList: ArrayList<Product>
    lateinit var productListTopSale: ArrayList<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()

        //
        sharedPrefs = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        userId = sharedPrefs.getInt("userId", -1)


        // retrofit
        iRetrofit = RetrofitHelper.createService(IRetrofit::class.java)

        // slider
        sliderView = view.findViewById(R.id.imageSlider)

        sliderAdapter = SliderAdapter(imageUrl)
        sliderView.setSliderAdapter(sliderAdapter)

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        val colorSelected = ContextCompat.getColor(requireContext(), R.color.primaryColor)
        val colorUnselected = ContextCompat.getColor(requireContext(), R.color.detailColor)
        sliderView.setIndicatorSelectedColor(colorSelected);
        sliderView.setIndicatorUnselectedColor(colorUnselected);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        // category
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcvCategory = view.findViewById(R.id.rcv_categories)
        rcvCategory.layoutManager = layoutManager
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = iRetrofit.getAllCategory().execute()
                if (response.isSuccessful) {
                    val categoryRes = response.body()
                    if (categoryRes != null && categoryRes.getStatus()) {
                        Log.e("categoryRes", "Response successful")
                        Log.e("categoryRes", categoryRes.toString())
                        categoryList = categoryRes.getCategories()

                        withContext(Dispatchers.Main) {
                            // Xử lý UI trên Main thread
                            categoryAdapter = CategoryAdapter(requireContext(), categoryList)
                            rcvCategory.adapter = categoryAdapter
                            // set listen interface
                            categoryAdapter.setCategoryAdapterListener(this@HomeFragment)
                        }
                    } else {
                        Log.e("categoryRes", "Response unsuccessful")
                    }
                } else {
                    // Xử lý lỗi khi gọi API lấy danh sách category
                    Log.e("Categories", "Response not successful: ${response.code()}")
                }
            } catch (e: Exception) {
                // Xử lý lỗi khi gọi API
                Log.e("Categories", "onFailure: ${e.message}")
            }
        }



        // top_sale product
        val layoutManagerProductTopSale = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcvProductTopSale = view.findViewById(R.id.rcv_top_sale)
        rcvProductTopSale.layoutManager = layoutManagerProductTopSale
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = iRetrofit.getTopProduct().execute()
                if (response.isSuccessful) {
                    val productRes = response.body()
                    if (productRes != null && productRes.getStatus()) {
                        Log.e("top sale product ", "Response successful")
                        Log.e("top sale product ", productRes.toString())
                        productListTopSale = productRes.getProducts()

                        withContext(Dispatchers.Main) {
                            // Xử lý UI trên Main thread
                            productAdapterTopSale = TopProductAdapter(requireContext(), productListTopSale)
                            rcvProductTopSale.adapter = productAdapterTopSale
                            // set listen interface
                            productAdapterTopSale.setTopProductAdapter(this@HomeFragment)
                        }
                    } else {
                        Log.e("productRes", "Response unsuccessful")
                    }
                } else {
                    // Xử lý lỗi khi gọi API lấy danh sách category
                    Log.e("productRes", "Response not successful: ${response.code()}")
                }
            } catch (e: Exception) {
                // Xử lý lỗi khi gọi API
                Log.e("productRes", "onFailure: ${e.message}")
            }
        }

        // new product
        val layoutManagerNewProduct = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcvNewProduct = view.findViewById(R.id.rcv_new_product)
        rcvNewProduct.layoutManager = layoutManagerNewProduct
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = iRetrofit.getNewProduct().execute()
                if (response.isSuccessful) {
                    val productRes = response.body()
                    if (productRes != null && productRes.getStatus()) {
                        Log.e("new product ", "Response successful")
                        Log.e("new product ", productRes.getMessage())
                        Log.e("new product ", productRes.toString())
                        newProductList = productRes.getProducts()

                        withContext(Dispatchers.Main) {
                            // Xử lý UI trên Main thread
                            newProductAdapter = ProductAdapter(requireContext(), newProductList)
                            rcvNewProduct.adapter = newProductAdapter
                            // set listen interface
                            newProductAdapter.setProductAdapterListener(this@HomeFragment)
                        }
                    } else {
                        Log.e("new product", "Response unsuccessful")
                    }
                } else {
                    // Xử lý lỗi khi gọi API lấy danh sách category
                    Log.e("new product", "Response not successful: ${response.code()}")
                }
            } catch (e: Exception) {
                // Xử lý lỗi khi gọi API
                Log.e("new product", "onFailure: ${e.message}")
            }
        }


        // discount product
        val layoutManagerDiscountProduct = GridLayoutManager(context, 2)
        layoutManagerDiscountProduct.spanCount = 2
        rcvDiscountProduct = view.findViewById(R.id.rcv_recommend)
        rcvDiscountProduct.layoutManager = layoutManagerDiscountProduct
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = iRetrofit.getDiscountProduct().execute()
                if (response.isSuccessful) {
                    val productRes = response.body()
                    if (productRes != null && productRes.getStatus()) {
                        Log.e("discount product", "Response successful")
                        Log.e("discount product", productRes.getMessage())
                        Log.e("discount product", productRes.toString())
                        discountProductList = productRes.getProducts()

                        withContext(Dispatchers.Main) {
                            // Xử lý UI trên Main thread
                            discountProductAdapter = ProductAdapter(requireContext(), discountProductList)
                            rcvDiscountProduct.adapter = discountProductAdapter
                            // set listen interface
                            discountProductAdapter.setProductAdapterListener(this@HomeFragment)
                        }
                    } else {
                        Log.e("discount product", "Response unsuccessful")
                    }
                } else {
                    Log.e("discount product", "Response not successful: ${response.code()}")
                }
            } catch (e: Exception) {
                // Xử lý lỗi khi gọi API
                Log.e("productRes", "onFailure: ${e.message}")
            }
        }


    }

    private fun dataInitialize() {
        // slider
        imageUrl = ArrayList()
        imageUrl.add("https://cdn.nguyenkimmall.com/images/companies/_1/Content/dien-lanh/tu-lanh/trai-cay-tu-lanh.jpg")
        imageUrl.add("https://bizweb.dktcdn.net/100/025/663/files/tra-hoa-qua-1.jpg?v=1620664269182")
        imageUrl.add("https://cdn.tgdd.vn/Files/2019/11/26/1222471/7-cach-chon-rau-cu-qua-tuoi-ngon-cuc-don-gian-202202141603009593.jpg")

    }

    override fun onCategoryClick(itemId: Int, name: String) {
        var intent = Intent(requireContext(), ListProductActivity::class.java)
        intent.putExtra("categoryId", itemId)
        intent.putExtra("categoryName", name)
        startActivity(intent)
    }

    override fun getProductById(itemId: Int) {
        var intent = Intent(requireContext(), DetailProductActivity::class.java)
        intent.putExtra("productId", itemId)
        startActivity(intent)
    }

    override fun addToFavorite(itemId: Int) {
        Log.e("userId ", userId.toString())
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = iRetrofit.addToFavorites(userId, itemId).execute()
                if (response.isSuccessful) {
                    val favoriteRes = response.body()
                    if (favoriteRes != null && favoriteRes.getStatus()) {
                        Log.e("favoriteRes ", favoriteRes.getMessage())
                        Log.e("favoriteRes ", favoriteRes.toString())
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), favoriteRes.getMessage(), Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.e("favoriteRes", "Response unsuccessful")
                        if (favoriteRes != null) {
                            Log.e("favoriteRes ", favoriteRes.getMessage())
                            withContext(Dispatchers.Main) {
                                Toast.makeText(requireContext(), favoriteRes.getMessage(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    // Xử lý lỗi khi gọi API lấy danh sách category
                    Log.e("favoriteRes", "Response not successful: ${response.code()}")
                }
            } catch (e: Exception) {
                // Xử lý lỗi khi gọi API
                Log.e("favoriteRes", "onFailure: ${e.message}")
            }
        }
    }

}

