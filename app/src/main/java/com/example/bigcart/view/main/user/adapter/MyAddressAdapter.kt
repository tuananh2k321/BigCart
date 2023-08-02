package com.example.bigcart.view.main.user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigcart.R
import com.example.bigcart.model.Address

class MyAddressAdapter(context: Context, listMyAddress: ArrayList<Address>):
    RecyclerView.Adapter<MyAddressAdapter.MyAddressViewHolder>() {

    private var context: Context
    private var listMyAddress: ArrayList<Address>

    init {
        this.context = context
        this.listMyAddress = listMyAddress
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAddressViewHolder {
        var itemView = LayoutInflater.from(parent.context).
            inflate(R.layout.item_my_address, parent, false)
        return MyAddressViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listMyAddress.size
    }

    override fun onBindViewHolder(holder: MyAddressViewHolder, position: Int) {
        var currentItem = listMyAddress[position]
        holder.tvAddress.text = currentItem.getAddress()
        holder.tvName.text = currentItem.getName()
        holder.tvPhoneNumber.text = "+84 "+currentItem.getPhoneNumber()

        //default
        if (currentItem.getDefault()) {
            holder.tvDefault.visibility = View.VISIBLE
        } else {
            holder.tvDefault.visibility = View.GONE
        }

    }

    fun deleteItem(absoluteAdapterPosition: Int) {
        listMyAddress.removeAt(absoluteAdapterPosition)
        // update user interface
        notifyItemRemoved(absoluteAdapterPosition)
    }

    class MyAddressViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvAddress: TextView = itemView.findViewById(R.id.tv_address)
        var tvDefault: TextView = itemView.findViewById(R.id.tv_default)
        var tvPhoneNumber: TextView = itemView.findViewById(R.id.tv_phone_number)
        var itemMyAddress: CardView = itemView.findViewById(R.id.item_my_address)
    }
}