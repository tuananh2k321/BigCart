package com.example.bigcart.view.main.user.view

import android.content.Intent
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigcart.R
import com.example.bigcart.databinding.ActivityMyAddressBinding
import com.example.bigcart.model.Address
import com.example.bigcart.view.main.user.adapter.MyAddressAdapter
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class MyAddressActivity : AppCompatActivity() {
    private var _binding: ActivityMyAddressBinding? = null
    private val binding: ActivityMyAddressBinding
        get() = _binding!!

    private lateinit var myAddressList: ArrayList<Address>
    private lateinit var myAddressAdapter: MyAddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMyAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataInit()

        with(binding) {
            // back
            imgBack.setOnClickListener{
                finish()
            }

            // start new address activity
            btnAddNewAddress.setOnClickListener{
                var intent = Intent(applicationContext, NewAddressActivity::class.java)
                startActivity(intent)
            }


            // recycle view my address
            var layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            rcvMyAddress.layoutManager = layoutManager
            myAddressAdapter = MyAddressAdapter(applicationContext, myAddressList)
            rcvMyAddress.adapter = myAddressAdapter

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
                    myAddressAdapter.deleteItem(viewHolder.absoluteAdapterPosition)
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
                                applicationContext,
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
            itemTouchHelper.attachToRecyclerView(rcvMyAddress)
        }
    }

    private fun dataInit() {
        myAddressList = arrayListOf<Address>(
            Address(1, "ql1A, Đông Hưng Thuận, Q12, TpHCM", true, "Trần Tuấn Anh", "0921011337", 1),
            Address(2, "Phan Văn Trị, Gò Vấp, TpHCM", false, "Trần Anh Trí", "0359832547", 1),
            Address(3, "Tô Ký, Trung Mỹ Tây, Q12, TpHCM", false, "Trần Tuấn Anh", "0368004241", 1),
        )
    }
}