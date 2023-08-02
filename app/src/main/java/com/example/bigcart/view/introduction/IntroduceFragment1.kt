package com.example.bigcart.view.introduction

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.bigcart.R
import com.example.bigcart.view.LoginActivity


class IntroduceFragment1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_introduce1, container, false)

        val next1 = view.findViewById<TextView>(R.id.tv_next1)
        val skip1 = view.findViewById<TextView>(R.id.tv_skip1)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_page_introduction)

        next1.setOnClickListener{
            viewPager?.currentItem = 1
        }

        skip1.setOnClickListener{
            val intent = Intent(requireContext(), LoginActivity()::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }

}