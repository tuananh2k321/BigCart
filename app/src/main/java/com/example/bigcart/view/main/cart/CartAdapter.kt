package com.example.bigcart.view.main.cart

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigcart.R
import com.example.bigcart.model.Product

class CartAdapter(context: Context, productList: ArrayList<Product>):
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private lateinit var context: Context
    private lateinit var productList: ArrayList<Product>
    private var subtotal: Double = 0.0
    init {
        this.context = context
        this.productList = productList
    }

    // listen even change
    private var listener: CartAdapterListener? = null
    fun setCartAdapterListener(listener: CartAdapterListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.item_cart, parent, false)

        return CartViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        var currentItem = productList[position]

        //holder.imgCart.setImageResource(currentItem.getImage())
        holder.tvName.text = currentItem.getName()
        holder.tvPrice.text = "$ "+ currentItem.getPrice().toString()
        holder.tvWeight.text = currentItem.getWeight().toString() + " g"

        var quantity: Int = 1
        var total: Double = currentItem.getPrice()
        holder.imgPlus.setOnClickListener {
            Log.d("imgPlus", currentItem.getId().toString())
            quantity++
            holder.tvQuantity.text = quantity.toString()
            total += currentItem.getPrice()
            subtotal += currentItem.getPrice()
            updateSubtotal()
            Log.d("imgPlus subtotal", subtotal.toString())
            holder.tvPrice.text = "$ $total"
        }

        holder.imgMinus.setOnClickListener {
            Log.d("imgMinus", currentItem.getId().toString())
            if (quantity > 1) {
                quantity--
                holder.tvQuantity.text = quantity.toString()
                total -= currentItem.getPrice()
                subtotal -= currentItem.getPrice()
                updateSubtotal()
                Log.d("imgMinus subtotal", subtotal.toString())
                holder.tvPrice.text = "$ $total"
            }
        }
    }

    class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imgCart: ImageView = itemView.findViewById(R.id.img_cart)
        var tvName: TextView = itemView.findViewById(R.id.tv_name_product)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        var tvWeight: TextView = itemView.findViewById(R.id.tv_weight)
        var tvQuantity: TextView = itemView.findViewById(R.id.tv_quantity)
        var imgPlus: ImageView = itemView.findViewById(R.id.img_plus)
        var imgMinus: ImageView = itemView.findViewById(R.id.img_minus)
    }

    // delete
    fun deleteItem(absoluteAdapterPosition: Int) {
        productList.removeAt(absoluteAdapterPosition)
        // update user interface
        notifyItemRemoved(absoluteAdapterPosition)
    }

    // checkout
    fun initSubtotal(): Double {
        for (item in productList) {
            subtotal += item.getPrice()
        }
        return subtotal
    }

    private fun updateSubtotal() {
        Log.d("updateSubtotal", subtotal.toString())
        listener?.onCartItemsChanged()
    }

    fun finalSubtotal(): Double{

        Log.d("finalSubtotal", subtotal.toString())
        return subtotal
    }
}