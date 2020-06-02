package com.example.meditate2.auth

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter (fm : FragmentManager, behavior:Int) :
    FragmentStatePagerAdapter(fm,behavior) {
    private val fragmentList: ArrayList<Fragment> = ArrayList()
    override fun getItem(i: Int): Fragment {
        return fragmentList[i]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }
}