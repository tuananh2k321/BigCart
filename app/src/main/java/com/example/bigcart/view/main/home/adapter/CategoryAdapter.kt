package com.example.bigcart.view.main.home.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bigcart.R
import com.example.bigcart.model.Category
import com.example.bigcart.view.main.home.ProductInterface
import com.example.bigcart.view.main.home.view.ListProductActivity


class CategoryAdapter(context: Context, listCategory: ArrayList<Category>):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private lateinit var context: Context
    private lateinit var listCategory: ArrayList<Category>

    init {
        this.context = context
        this.listCategory = listCategory
    }

    // listen even change
    private var listener: ProductInterface? = null
    fun setCategoryAdapterListener(listener: ProductInterface) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.item_listview_categories, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = listCategory[position]
        holder.name.text = currentItem.getName()
        Glide.with(context).load(currentItem.getImage()).fitCenter().into(holder.image)
        holder.cardView.setCardBackgroundColor(Color.parseColor(currentItem.getBgColor()))

        holder.cardView.setOnClickListener{
            Log.e("id category", "id category:" + currentItem.getId().toString())
            listener?.onCategoryClick(currentItem.getId(), currentItem.getName())
        }
    }

    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_name)
        val image: ImageView = itemView.findViewById(R.id.img_category)
        val cardView: CardView = itemView.findViewById(R.id.card_view_category)
    }
}