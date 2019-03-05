package com.tlgbltcn.app.weather.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tlgbltcn.app.weather.ui.fragment.fifteendays.FifteenDaysFragment
import com.tlgbltcn.app.weather.ui.fragment.fivedays.FiveDaysFragment
import com.tlgbltcn.app.weather.ui.fragment.today.TodayFragment

class MainTabAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    private val mFragmentList = arrayListOf<Fragment>()


    init {
        val todayFragment = TodayFragment()
        val fiveDaysFragment = FiveDaysFragment()
        val fifteenDaysFragment = FifteenDaysFragment()

        mFragmentList.add(todayFragment)
        mFragmentList.add(fiveDaysFragment)
        mFragmentList.add(fifteenDaysFragment)
    }

    override fun getItem(position: Int): Fragment = mFragmentList[position]

    override fun getCount(): Int = mFragmentList.size
}