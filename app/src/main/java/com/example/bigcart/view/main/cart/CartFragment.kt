package com.example.bigcart.view.main.cart

import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.bigcart.R
import com.example.bigcart.model.Product
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class CartFragment : Fragment(), CartAdapterListener {

    private lateinit var rcvCart: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartList: ArrayList<Product>

    // checkout
    private lateinit var  tvSubtotal: TextView
    private lateinit var tvShippingCharge: TextView
    private lateinit var tvTotal: TextView
    private var subtotal: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()

        var layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcvCart = view.findViewById(R.id.rcv_cart)
        rcvCart.layoutManager = layoutManager
        cartAdapter = CartAdapter(requireContext(), cartList)
        rcvCart.adapter = cartAdapter

        //checkout
        tvSubtotal = view.findViewById(R.id.tv_subtotal)
        tvShippingCharge = view.findViewById(R.id.tv_shipping_charge)
        tvTotal = view.findViewById(R.id.tv_total)

        //set listen interface
        cartAdapter.setCartAdapterListener(this)

        var shippingCharge: Double = 15.0
        subtotal = cartAdapter.initSubtotal()
        tvSubtotal.text = "$ $subtotal"
        tvShippingCharge.text = "$ $shippingCharge"
        tvTotal.text = (subtotal+shippingCharge).toString()



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
                cartAdapter.deleteItem(viewHolder.absoluteAdapterPosition)
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
        itemTouchHelper.attachToRecyclerView(rcvCart)
    }

    private fun initData() {
        cartList = arrayListOf<Product>(
            Product(1, "Peach", "New", "bc", "abc",true, 5.0, 500.0, 1),
            Product(2, "Avocado", "-18%", "bc", "abc",false, 7.0, 500.0, 5),
            Product(3, "Pineapple", "New", "bc", "abc",true, 6.0, 500.0, 18),
            Product(4, "Grapes", "New", "bc", "abc",false, 4.0, 500.0, 1),
            Product(5, "Pomegranates", "New", "bc", "abc",true, 5.0, 500.0, 8),
            Product(6, "Broccoli", "New", "bc", "abc",true, 5.0, 500.0, 1),
        )
    }

    override fun onCartItemsChanged() {
        var shippingCharge: Double = 15.0
        subtotal = cartAdapter.finalSubtotal()
        tvSubtotal.text = "$ $subtotal"
        tvShippingCharge.text = "$ $shippingCharge"
        tvTotal.text = (subtotal+shippingCharge).toString()
    }
}