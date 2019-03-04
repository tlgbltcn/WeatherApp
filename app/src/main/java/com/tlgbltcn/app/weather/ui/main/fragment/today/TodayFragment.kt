package com.tlgbltcn.app.weather.ui.main.fragment.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.R
import com.tlgbltcn.app.weather.core.base.BaseFragment
import com.tlgbltcn.app.weather.databinding.FragmentTodayBinding
import com.tlgbltcn.app.weather.ui.main.MainActivity
import com.tlgbltcn.app.weather.ui.main.SharedViewModel
import com.tlgbltcn.app.weather.utils.location.LocationHandler


class TodayFragment : BaseFragment<TodayFragmentViewModel, FragmentTodayBinding>(TodayFragmentViewModel::class.java), TodayHandler, LocationHandler {

    override fun getLayoutRes(): Int = R.layout.fragment_today

    private lateinit var sharedViewModel : SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val result = super.onCreateView(inflater, container, savedInstanceState)
        (activity?.application as App).component.inject(this)
        mBinding.viewModel = viewModel
        mBinding.handler = this
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        (activity as MainActivity).getLatLon(object : LocationHandler {
            override fun onLocation(lat: Double, lon: Double) {
                loadData(lat,lon)
            }

        })

        return result
    }

    private fun loadData(lat : Double, lon : Double) {
        val todayData = viewModel.getTodayByCoord(lat, lon)
        todayData.let {
            todayData.observe(this, Observer { resource ->
                mBinding.todayEntity = resource.data
                mBinding.resources = resource

            })
        }

    }

    override fun onLocation(lat: Double, lon: Double) {
        loadData(lat,lon)
    }

    override fun onClick() {

    }
}