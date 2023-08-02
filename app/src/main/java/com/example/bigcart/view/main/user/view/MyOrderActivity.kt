package com.example.bigcart.view.main.user.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigcart.R
import com.example.bigcart.databinding.ActivityMyOrderBinding
import com.example.bigcart.model.MyOrder
import com.example.bigcart.view.main.user.adapter.MyOrderAdapter

class MyOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyOrderBinding

    private lateinit var myOrderList: ArrayList<MyOrder>
    private lateinit var myOderAdapter: MyOrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataInit()
        with(binding) {
            // back
            binding.imgBack.setOnClickListener{
                finish()
            }

            // my order list
            var layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            rcvMyOrder.layoutManager = layoutManager
            myOderAdapter = MyOrderAdapter(applicationContext, myOrderList)
            rcvMyOrder.adapter = myOderAdapter
        }
    }

    private fun dataInit() {
        myOrderList = arrayListOf(
            MyOrder(1, true, true, false, false,
                "02/07/2023", "04/07/2023", "06/07/2023", "09/07/2023", 1),
            MyOrder(2, true, true, true, false,
                "02/07/2023", "04/07/2023", "06/07/2023", "09/07/2023", 2),
            MyOrder(3, true, false, false, false,
                "02/07/2023", "04/07/2023", "06/07/2023", "09/07/2023", 3)
        )
    }
}