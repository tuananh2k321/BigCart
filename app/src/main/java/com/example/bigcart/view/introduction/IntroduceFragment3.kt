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


class IntroduceFragment3 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_introduce3, container, false)

        val finish = view.findViewById<TextView>(R.id.tv_finish)
        val back3 = view.findViewById<TextView>(R.id.tv_back3)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_page_introduction)

        finish.setOnClickListener{
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        back3.setOnClickListener{
            viewPager?.currentItem = 1
        }

        return view
    }

}