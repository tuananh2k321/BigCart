package com.example.bigcart.view.main.user.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.bigcart.R
import com.example.bigcart.databinding.ActivityNewAddressBinding

class NewAddressActivity : AppCompatActivity() {
    private var _binding:ActivityNewAddressBinding? = null
    private val binding: ActivityNewAddressBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            imgBack.setOnClickListener{
                finish()
            }

            btnDefault.setOnCheckedChangeListener{buttonView, isChecked ->
                if (isChecked) {
                    Log.d("isChecked", isChecked.toString())
                    val primaryColor = ContextCompat.getColor(applicationContext, R.color.primaryColor)
                    tvMakeDefault.setTextColor(primaryColor)
                } else {
                    Log.d("isChecked", isChecked.toString())
                    val detailColor = ContextCompat.getColor(applicationContext, R.color.detailColor)
                    tvMakeDefault.setTextColor(detailColor)
                }
            }
        }
    }
}