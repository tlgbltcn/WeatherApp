package com.tlgbltcn.app.weather.ui.fragment.fivedays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.R
import com.tlgbltcn.app.weather.core.BaseFragment
import com.tlgbltcn.app.weather.databinding.FragmentWeeklyBinding

class FiveDaysFragment : BaseFragment<FiveDaysViewModel, FragmentWeeklyBinding>(FiveDaysViewModel::class.java) {

    override fun getLayoutRes(): Int = R.layout.fragment_weekly

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val result = super.onCreateView(inflater, container, savedInstanceState)
        (activity?.application as App).component.inject(this)
        mBinding.viewModel = viewModel

        return result
    }
}