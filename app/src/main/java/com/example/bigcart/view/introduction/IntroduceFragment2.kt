package com.example.bigcart.view.introduction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.bigcart.R


class IntroduceFragment2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_introduce2, container, false)

        val next2 = view.findViewById<TextView>(R.id.tv_next2)
        val back2 = view.findViewById<TextView>(R.id.tv_back2)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_page_introduction)

        next2.setOnClickListener{
            viewPager?.currentItem = 2
        }

        back2.setOnClickListener{
            viewPager?.currentItem = 0
        }

        return view
    }

}