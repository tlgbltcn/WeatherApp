package com.tlgbltcn.app.weather.ui.main.fragment.fifteendays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.R
import com.tlgbltcn.app.weather.core.base.BaseFragment
import com.tlgbltcn.app.weather.databinding.FragmentYesterdayBinding

class FifteenDaysFragment : BaseFragment<FifteenDaysViewModel, FragmentYesterdayBinding>(FifteenDaysViewModel::class.java) {
    override fun getLayoutRes(): Int = R.layout.fragment_yesterday


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val result = super.onCreateView(inflater, container, savedInstanceState)
        (activity?.application as App).component.inject(this)
        mBinding.viewModel = viewModel

        return result
    }


}