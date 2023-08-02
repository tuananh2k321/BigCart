package com.example.bigcart.view.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigcart.R
import com.example.bigcart.model.User

class HistorySearchAdapter (listHistorySearch: Array<String>, context: Context,):
    RecyclerView.Adapter<HistorySearchAdapter.HistorySearchViewHolder>()
{
    private var listHistorySearch: Array<String>
    private var context: Context

    init {
        this.listHistorySearch = listHistorySearch
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorySearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).
            inflate(R.layout.item_history_search, parent, false)
        return HistorySearchViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listHistorySearch.size
    }

    override fun onBindViewHolder(holder: HistorySearchViewHolder, position: Int) {
        var currentItem = listHistorySearch[position]
        holder.tvHistorySearch.text = currentItem
    }

    class HistorySearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvHistorySearch: TextView = itemView.findViewById(R.id.tv_history_search)
    }
}