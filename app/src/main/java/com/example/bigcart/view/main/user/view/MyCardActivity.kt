package com.example.bigcart.view.main.user.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigcart.R
import com.example.bigcart.databinding.ActivityMyCardBinding
import com.example.bigcart.model.Card
import com.example.bigcart.view.main.user.adapter.MyCardAdapter

class MyCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyCardBinding

    private lateinit var listCard: ArrayList<Card>
    private lateinit var myCardAdapter: MyCardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataInit()

        with(binding) {
            // back
            imgBack.setOnClickListener {
                finish()
            }
            // list card
            var layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            rcvMyCard.layoutManager = layoutManager
            myCardAdapter = MyCardAdapter(applicationContext, listCard)
            rcvMyCard.adapter = myCardAdapter
        }
    }

    private fun dataInit() {
        listCard = arrayListOf(
            Card(1, "Tuan Anh", "17238271618273182736", "16/2027", "821", false),
            Card(1, "Tuan Anh", "17238271618273189281", "22/2028", "821", false),
            Card(1, "Tuan Anh", "17238271618273180291", "03/2027", "821", true)
        )
    }
}