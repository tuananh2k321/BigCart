package com.example.bigcart.view.main.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bigcart.R
import com.example.bigcart.model.Product

class FavoriteAdapter(context: Context, favoriteList: ArrayList<Product>):
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private lateinit var context: Context
    private lateinit var favoriteList: ArrayList<Product>

    init {
        this.context = context
        this.favoriteList = favoriteList
    }

    var listener: FavoriteInterFace? = null
    fun setFavoriteAdapterListener(listener: FavoriteInterFace) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView = LayoutInflater.from(parent.context).
                inflate(R.layout.item_favorite, parent, false)

        return FavoriteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        var currentItem = favoriteList[position]
//        holder.imgFavorite.setImageResource(currentItem.getImage())
        holder.tvName.text = currentItem.getName()
        holder.tvPrice.text = "$ " + currentItem.getPrice().toString()
        holder.tvWeight.text = currentItem.getWeight().toString() + " g"
        Glide.with(context).load(currentItem.getImage()).fitCenter().into(holder.imgFavorite)

        // add to cart
        holder.lnlAddToCart.setOnClickListener{
            holder.tvAddToCart.text = "Successfully"
            holder.imgAddToCart.setImageResource(R.drawable.icon_check_24)
        }
    }


    class FavoriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgFavorite: ImageView = itemView.findViewById(R.id.img_favorite)
        var tvName: TextView = itemView.findViewById(R.id.tv_name_product)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        var tvWeight: TextView = itemView.findViewById(R.id.tv_weight)

        // add to cart
        var lnlAddToCart: LinearLayout = itemView.findViewById(R.id.lnl_add_to_cart)
        var tvAddToCart: TextView = itemView.findViewById(R.id.tv_add_to_cart)
        var imgAddToCart: ImageView = itemView.findViewById(R.id.img_add_to_cart)
    }

    fun deleteItem(absoluteAdapterPosition: Int) {
        favoriteList.removeAt(absoluteAdapterPosition)
        // update user interface
        notifyItemRemoved(absoluteAdapterPosition)
        val itemId = favoriteList[absoluteAdapterPosition].getId()
        listener?.deleteFavorite(itemId)
    }
}