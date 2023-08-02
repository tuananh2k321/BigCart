package com.example.bigcart.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bigcart.R
import com.example.bigcart.databinding.ActivityMainBinding
import com.example.bigcart.m_view_model.UserViewModel
import com.example.bigcart.view.main.cart.CartFragment
import com.example.bigcart.view.main.favorite.FavoriteFragment
import com.example.bigcart.view.main.home.view.HomeFragment
import com.example.bigcart.view.main.user.view.UserFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[UserViewModel::class.java]
        Log.e(" Main userLiveData", userViewModel.getUserLiveData().value?.toString() ?: "null")
        replaceFragment(HomeFragment())

        with(binding) {
            bottomNavigationView.setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.home -> {
                        replaceFragment(HomeFragment())
                    }
                    R.id.favorite -> {
                        replaceFragment(FavoriteFragment())
                    }
                    R.id.cart -> {
                        replaceFragment(CartFragment())
                    }
                    R.id.user -> {
                        replaceFragment(UserFragment())
                    }

                    else -> {

                    }
                }

                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fr_layout_main, fragment)
        fragmentTransaction.commit()
    }
}