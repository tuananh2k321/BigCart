package com.example.bigcart.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.bigcart.R
import com.example.bigcart.Utilities.CheckNetwork
import com.example.bigcart.view.introduction.IntroduceActivity


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val networkConnection = CheckNetwork(applicationContext)

        networkConnection.observe(this) {
            if (it) {
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, IntroduceActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 2000)
            } else {
                Toast.makeText(this, "NO INTERNET", Toast.LENGTH_LONG).show()
            }
        }
    }

}