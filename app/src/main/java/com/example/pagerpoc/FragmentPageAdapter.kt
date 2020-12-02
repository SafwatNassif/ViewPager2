package com.example.pagerpoc

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerFragmentAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 3


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment()
            1 -> SecFragment()
            2 -> ThreedFragment()
            else -> ThreedFragment()
        }
    }


}