package com.example.bigcart.view.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.bigcart.R
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(imageUrl: ArrayList<String>): SliderViewAdapter<SliderAdapter.SliderViewHolder>() {
    var sliderImage: ArrayList<String> = imageUrl

    override fun getCount(): Int {
        return sliderImage.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        var inflater: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_slider, null)
        return SliderViewHolder(inflater)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        if (viewHolder!= null) {
            Glide.with(viewHolder.itemView).load(sliderImage.get(position)).fitCenter().into(viewHolder.imageView)
        }
    }

    class SliderViewHolder(itemView: View): SliderViewAdapter.ViewHolder(itemView) {
        var imageView:ImageView = itemView.findViewById(R.id.img_item)
    }
}