package com.example.bigcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigcart.databinding.ActivityNotifyVerifyEmailBinding
import com.example.bigcart.view.LoginActivity

class NotifyVerifyEmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotifyVerifyEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifyVerifyEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}