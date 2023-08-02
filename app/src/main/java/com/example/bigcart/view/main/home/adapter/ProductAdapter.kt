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

class ProductAdapter(context: Context, listProduct: ArrayList<Product>):
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private lateinit var context: Context
    private lateinit var listProduct: ArrayList<Product>

    init {
        this.context = context
        this.listProduct = listProduct
    }

    var listener: ProductInterface? = null
    fun setProductAdapterListener(listener: ProductInterface) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).
            inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var currentItem = listProduct[position]
        holder.name.text = currentItem.getName()
        holder.status.text = currentItem.getStatusProduct()
        holder.weight.text = currentItem.getWeight().toString() + " g"
        Glide.with(context).load(currentItem.getImage()).fitCenter().into(holder.imgProduct)
        holder.price.text = "$ " + currentItem.getPrice().toString()

        // set favorite
        if (currentItem.getFavorite()) {
            currentItem.setFavorite(true)
            holder.favorite.setImageResource(R.drawable.icons8_favorite_24_d)
        }
        holder.favorite.setOnClickListener {
            Log.d("favorite", currentItem.getId().toString())
            currentItem.setFavorite(true)
            holder.favorite.setImageResource(R.drawable.icons8_favorite_24_d)
            Log.e("favorite", currentItem.getFavorite().toString())
            Log.e("Product Adapter", "id product: "+currentItem.getId().toString())
            listener?.addToFavorite(currentItem.getId())
        }

        // set add to cart
        holder.lnlAddToCart.setOnClickListener{
            Log.d("lnlAddToCart", "onclick ")
            holder.tvAddToCart.text = "Successfully"
            holder.imgAddToCart.setImageResource(R.drawable.icon_check_24)
        }

        // get id product
        holder.cdProduct.setOnClickListener{
            Log.e("Product Adapter", "id product: "+currentItem.getId().toString())
            listener?.getProductById(currentItem.getId())
        }


    }

    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_name_product)
        val status: TextView = itemView.findViewById(R.id.tv_status)
        val weight: TextView = itemView.findViewById(R.id.tv_weight)
        val imgProduct: ImageView = itemView.findViewById(R.id.img_product)
        val price: TextView = itemView.findViewById(R.id.tv_price)
        val favorite: ImageView = itemView.findViewById(R.id.img_favorite)
        val cdProduct: CardView = itemView.findViewById(R.id.cd_product)

        // Add to cart
        val lnlAddToCart: LinearLayout = itemView.findViewById(R.id.lnl_add_to_cart)
        val tvAddToCart: TextView = itemView.findViewById(R.id.tv_add_to_cart)
        val imgAddToCart: ImageView = itemView.findViewById(R.id.img_add_to_cart)

    }
}
