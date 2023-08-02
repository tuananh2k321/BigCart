package com.example.bigcart.view.main.user.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bigcart.R
import com.example.bigcart.model.MyOrder

class MyOrderAdapter(context: Context, myOrderList: ArrayList<MyOrder>): RecyclerView.Adapter<MyOrderAdapter.MyOrderViewHolder>() {

    private var context: Context
    private var myOrderList: ArrayList<MyOrder>

    init {
        this.context = context
        this.myOrderList = myOrderList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrderViewHolder {
        var itemView = LayoutInflater.from(parent.context).
            inflate(R.layout.item_my_order, parent, false)
        return MyOrderViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return myOrderList.size
    }

    override fun onBindViewHolder(holder: MyOrderViewHolder, position: Int) {
        var currentItem = myOrderList[position]

        // product

        // schedule
        holder.tvOrderPlacedDate.text = currentItem.getOrderPlaceDate()
        holder.tvOrderConfirmDate.text = currentItem.getOrderConfirmDate()
        holder.tvOrderShippedDate.text = currentItem.getOrderShippedDate()
        holder.tvSuccessDate.text = currentItem.getSuccessDate()

        var isVisible: Boolean = true
        holder.imgArrow.setOnClickListener{
            if (isVisible) {
                holder.viewSchedule.visibility = View.VISIBLE
                holder.imgArrow.setImageResource(R.drawable.icon_arrow_bottom_2)

                isVisible = !isVisible
            } else {
                holder.viewSchedule.visibility = View.GONE
                holder.imgArrow.setImageResource(R.drawable.icon_arrow_top_2)
                isVisible = !isVisible
            }
        }

        // check
        // order place
        if (currentItem.getOrderPlace()) {
            holder.imgOrderPlaced.setImageResource(R.drawable.icon_check_24)
            holder.imgOrderPlaced.setColorFilter(ContextCompat.getColor(context ,R.color.primaryColor));
            holder.tvOrderPlaced.setTextColor(ContextCompat.getColor(context ,R.color.primaryColor))
        } else {
            holder.imgOrderPlaced.setImageResource(R.drawable.icon_uncheck)
        }

        // order confirm
        if (currentItem.getOrderConfirm()) {
            holder.imgOrderConfirm.setImageResource(R.drawable.icon_check_24)
            holder.imgOrderConfirm.setColorFilter(ContextCompat.getColor(context ,R.color.primaryColor));
            holder.tvOrderConfirm.setTextColor(ContextCompat.getColor(context ,R.color.primaryColor))
        } else {
            holder.imgOrderConfirm.setImageResource(R.drawable.icon_uncheck)
        }

        // order shipped
        if (currentItem.getOrderShipped()) {
            holder.imgOrderShipped.setImageResource(R.drawable.icon_check_24)
            holder.imgOrderShipped.setColorFilter(ContextCompat.getColor(context ,R.color.primaryColor));
            holder.tvOrderShipped.setTextColor(ContextCompat.getColor(context ,R.color.primaryColor))
        } else {
            holder.imgOrderShipped.setImageResource(R.drawable.icon_uncheck)
        }

        // successfully receive
        if (currentItem.getSuccess()) {
            holder.imgSuccess.setImageResource(R.drawable.icon_check_24)
            holder.imgSuccess.setColorFilter(ContextCompat.getColor(context ,R.color.primaryColor));
            holder.tvSuccess.setTextColor(ContextCompat.getColor(context ,R.color.primaryColor))
        } else {
            holder.imgSuccess.setImageResource(R.drawable.icon_uncheck)
        }
    }

    class MyOrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // product
        var tvProduct: TextView = itemView.findViewById(R.id.tv_name)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        var tvQuantity: TextView = itemView.findViewById(R.id.tv_quantity)

        // schedule
        var tvOrderPlaced: TextView = itemView.findViewById(R.id.tv_order_placed)
        var tvOrderConfirm: TextView = itemView.findViewById(R.id.tv_order_confirmed)
        var tvOrderShipped: TextView = itemView.findViewById(R.id.tv_order_shipped)
        var tvSuccess: TextView = itemView.findViewById(R.id.tv_success)

        var tvOrderPlacedDate: TextView = itemView.findViewById(R.id.tv_order_placed_date)
        var tvOrderConfirmDate: TextView = itemView.findViewById(R.id.tv_order_confirm_date)
        var tvOrderShippedDate: TextView = itemView.findViewById(R.id.tv_order_shipped_date)
        var tvSuccessDate: TextView = itemView.findViewById(R.id.tv_success_date)

        // check
        var imgOrderPlaced: ImageView = itemView.findViewById(R.id.img_order_placed)
        var imgOrderConfirm: ImageView = itemView.findViewById(R.id.img_order_confirm)
        var imgOrderShipped: ImageView = itemView.findViewById(R.id.img_order_shipped)
        var imgSuccess: ImageView = itemView.findViewById(R.id.img_success)

        // view schedule
        var imgArrow: ImageView = itemView.findViewById(R.id.img_arrow)
        var viewSchedule: LinearLayout = itemView.findViewById(R.id.view_schedule)
    }
}