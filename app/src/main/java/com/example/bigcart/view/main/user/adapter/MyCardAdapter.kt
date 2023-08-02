package com.example.bigcart.view.main.user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigcart.R
import com.example.bigcart.model.Card

class MyCardAdapter(context: Context, listMyCard: ArrayList<Card>):
    RecyclerView.Adapter<MyCardAdapter.MyCardViewHolder>() {

    private var context:Context
    private var listMyCard: ArrayList<Card>

    init {
        this.context = context
        this.listMyCard = listMyCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardViewHolder {
        var itemView = LayoutInflater.from(parent.context).
            inflate(R.layout.item_card, parent, false)
        return MyCardViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listMyCard.size
    }

    override fun onBindViewHolder(holder: MyCardViewHolder, position: Int) {
        var currentItem = listMyCard[position]

        holder.tvName.text = currentItem.getName()
        val cardNumber = currentItem.getCardNumber()
        val fourDigitsLatest = cardNumber.takeLast(4)
        holder.tvCardNumber.text = "XXXX XXXX XXXX $fourDigitsLatest"
        if (currentItem.getDefault()) {
            holder.tvDefault.visibility = View.VISIBLE
        } else {
            holder.tvDefault.visibility = View.GONE
        }
    }

    class MyCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvDefault: TextView = itemView.findViewById(R.id.tv_default)
        var tvCardNumber: TextView = itemView.findViewById(R.id.tv_card_number)
    }
}