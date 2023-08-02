package com.example.bigcart.view.introduction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bigcart.R

class IntroduceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduce)

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.frm_container_view, IntroductionFragment())
            .commit()
    }
}