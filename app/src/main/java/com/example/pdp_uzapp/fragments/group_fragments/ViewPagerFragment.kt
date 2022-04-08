package com.example.pdp_uzapp.fragments.group_fragments

import android.os.Bundle
import android.view.FocusFinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.adapters.ViewPagerAdapter
import com.example.pdp_uzapp.databinding.FragmentViewPagerBinding
import com.example.pdp_uzapp.entity.Course
import com.google.android.material.tabs.TabLayoutMediator
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {
var course: Int = -1
    private val binding: FragmentViewPagerBinding by viewBinding(FragmentViewPagerBinding::bind)
    lateinit var viewPagerAdapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course = it.getInt("course_group")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabTitle = arrayOf("Ochilgan guruhlar", "Ochilyotgan guruhlar")
        viewPagerAdapter = fragmentManager?.let { ViewPagerAdapter(it, lifecycle) }!!
        binding.apply {
            viewpager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout, viewpager) { tab, position ->
                tab.text = tabTitle[position]
            }.attach()

            addd.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("course_group", course)
                findNavController().navigate(R.id.addGroupFragment)
            }
        }
    }
}