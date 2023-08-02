package com.example.bigcart.view.main.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bigcart.R
import com.example.bigcart.model.Product
import com.example.bigcart.view.main.home.ProductInterface

class TopProductAdapter(context: Context, listProduct: ArrayList<Product>):
    RecyclerView.Adapter<TopProductAdapter.TopProductViewHolder>(){

    private lateinit var context: Context
    private lateinit var listProduct: ArrayList<Product>

    init {
        this.context = context
        this.listProduct = listProduct
    }

    var listener: ProductInterface? = null
    fun setTopProductAdapter(listener: ProductInterface) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.item_top_sale_product, parent, false)
        return TopProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: TopProductViewHolder, position: Int) {
        var currentItem = listProduct[position]
        holder.name.text = currentItem.getName()
        holder.status.text = currentItem.getStatusProduct()
        Glide.with(context).load(currentItem.getImage()).fitCenter().into(holder.imgProduct)
        holder.price.text = "$ " + currentItem.getPrice().toString()
        holder.sold.text = currentItem.getPurchaseQuantity().toString()

        // set favorite
        if (currentItem.getFavorite()) {
            currentItem.setFavorite(true)
            holder.favorite.setImageResource(R.drawable.icons8_favorite_24_d)
        }
        holder.favorite.setOnClickListener {
            Log.d("favorite", currentItem.getId().toString())
            currentItem.setFavorite(true)
            holder.favorite.setImageResource(R.drawable.icons8_favorite_24_d)
            Log.d("favorite", currentItem.getFavorite().toString())
        }

        // set add to cart
        holder.imgAddToCart.setOnClickListener{
            Log.d("lnlAddToCart", "onclick ")
            holder.imgAddToCart.setImageResource(R.drawable.icon_check_24)
        }

        //transmit id
        holder.cvProduct.setOnClickListener{
            Log.e("TopProductAdapter", "productId: "+ currentItem.getId().toString())
            listener?.getProductById(currentItem.getId())
        }
    }

    class TopProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_name_product)
        val status: TextView = itemView.findViewById(R.id.tv_status)
        val imgProduct: ImageView = itemView.findViewById(R.id.img_product)
        val price: TextView = itemView.findViewById(R.id.tv_price)
        val favorite: ImageView = itemView.findViewById(R.id.img_favorite)
        val sold: TextView = itemView.findViewById(R.id.tv_sold)
        val cvProduct: CardView = itemView.findViewById(R.id.cv_product)
        // Add to cart
        val imgAddToCart: ImageView = itemView.findViewById(R.id.img_add_to_cart)
    }

}