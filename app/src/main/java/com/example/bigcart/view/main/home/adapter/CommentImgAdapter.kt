package com.example.bigcart.view.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bigcart.R

class CommentImgAdapter(context: Context, listImg: Array<String>):
    RecyclerView.Adapter<CommentImgAdapter.CommentImgViewHolder>()
{
    private var context: Context
    private var listImg: Array<String>

    init {
        this.context = context
        this.listImg = listImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentImgViewHolder {
        var itemView = LayoutInflater.from(parent.context).
            inflate(R.layout.item_comment_img, parent, false)
        return CommentImgViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listImg.size
    }

    override fun onBindViewHolder(holder: CommentImgViewHolder, position: Int) {
        var currentItem = listImg[position]
        Glide.with(context).load(currentItem).centerCrop().into(holder.img)
    }

    class CommentImgViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.img)
    }
}