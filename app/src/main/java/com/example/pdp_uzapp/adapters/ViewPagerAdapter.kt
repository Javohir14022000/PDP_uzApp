package com.example.pdp_uzapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pdp_uzapp.fragments.group_fragments.ActiveGroupFragment
import com.example.pdp_uzapp.fragments.group_fragments.NoActiveGroupFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            ActiveGroupFragment()
        } else {
            NoActiveGroupFragment()
        }

    }
}