package com.example.blackcoffer.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.blackcoffer.fragments.FragmentBusinesses
import com.example.blackcoffer.fragments.FragmentPersonal
import com.example.blackcoffer.fragments.FragmentServices

class AdapterViewPager(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return FragmentPersonal()
            1 -> return FragmentServices()
            2 -> return FragmentBusinesses()
        }
        return Fragment()
    }
    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Personal"
            1 -> return "Services"
            2 -> return "Businesses"
        }
        return super.getPageTitle(position)
    }
}