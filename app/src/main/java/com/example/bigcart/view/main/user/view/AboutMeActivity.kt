package com.example.bigcart.view.main.user.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigcart.R
import com.example.bigcart.databinding.ActivityAboutMeBinding

class AboutMeActivity : AppCompatActivity() {
    private var _binding: ActivityAboutMeBinding? = null
    private val binding: ActivityAboutMeBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutMeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            // back
            imgBack.setOnClickListener{
                finish()
            }
        }
    }
}