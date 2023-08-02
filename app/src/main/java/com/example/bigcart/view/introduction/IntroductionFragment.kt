package com.example.bigcart.view.introduction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.bigcart.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class IntroductionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_introdution, container, false)

        val fragmentList = arrayListOf<Fragment>(
            IntroduceFragment1(),
            IntroduceFragment2(),
            IntroduceFragment3()
        )

        val adapter = IntroduceViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val viewPager = view.findViewById<ViewPager2>(R.id.view_page_introduction)

        viewPager.adapter = adapter

        val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        wormDotsIndicator.attachTo(viewPager)

        return  view
    }


}