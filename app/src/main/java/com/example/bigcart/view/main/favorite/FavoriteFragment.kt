package com.example.bigcart.view.main.favorite


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigcart.R
import com.example.bigcart.model.Product
import com.example.bigcart.retrofit.IRetrofit
import com.example.bigcart.retrofit.RetrofitHelper
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteFragment : Fragment(), FavoriteInterFace {
    // retrofit
    private lateinit var iRetrofit: IRetrofit

    // sharePrefer
    private lateinit var sharedPrefs: SharedPreferences

    private lateinit var rcvFavorite: RecyclerView
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var favoritesList: ArrayList<Product>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInit()

        // retrofit
        iRetrofit = RetrofitHelper.createService(IRetrofit::class.java)

        // Initialize SharedPreferences with the application context
        sharedPrefs = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val email = sharedPrefs.getString("email", null)
        val userId = sharedPrefs.getInt("userId", -1)
        Log.e("email", email.toString() )
        Log.e("userId", userId.toString() )
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcvFavorite = view.findViewById(R.id.rcv_favorite)
        rcvFavorite.layoutManager = layoutManager


        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = iRetrofit.getFavorites(userId).execute()
                if (response.isSuccessful) {
                    val favoriteRes = response.body()
                    if (favoriteRes != null && favoriteRes.getStatus()) {
                        Log.e("favoriteRes ", "Response successful")
                        Log.e("favoriteRes ", favoriteRes.toString())
                        favoritesList = favoriteRes.getFavorites()

                        withContext(Dispatchers.Main) {
                            // Xử lý UI trên Main thread
                            favoriteAdapter = FavoriteAdapter(requireContext(), favoritesList)
                            rcvFavorite.adapter = favoriteAdapter
                            // set listen interface
                            favoriteAdapter.setFavoriteAdapterListener(this@FavoriteFragment)
                        }
                    } else {
                        Log.e("favoriteRes", "Response unsuccessful")
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


        // swipe list view
        val callback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                favoriteAdapter.deleteItem(viewHolder.absoluteAdapterPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.errorColor
                        )
                    )
                    .addActionIcon(R.drawable.icons8_trash_50)
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rcvFavorite)
    }

    private fun dataInit() {

    }

    override fun deleteFavorite(itemId: Int) {

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = iRetrofit.deleteFavoriteById(itemId).execute()
                Log.e("favoritesId ", itemId.toString())
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