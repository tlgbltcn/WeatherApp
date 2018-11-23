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
import com.tlgbltcn.app.weather.db.AppDatabase
import com.tlgbltcn.app.weather.ui.main.SharedViewModel
import com.tlgbltcn.app.weather.utils.extensions.toast
import javax.inject.Inject

class TodayFragment : BaseFragment<TodayFragmentViewModel, FragmentTodayBinding>(TodayFragmentViewModel::class.java), TodayHandler {

    override fun getLayoutRes(): Int = R.layout.fragment_today
    @Inject
    lateinit var db : AppDatabase

    private lateinit var sharedViewModel : SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val result = super.onCreateView(inflater, container, savedInstanceState)
        (activity?.application as App).component.inject(this)
        mBinding.viewModel = viewModel
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        mBinding.handler = this
        observeData()

        return result
    }

    private fun observeData() {
        sharedViewModel.validate.observe(this, Observer {
            if (it == true){
                loadData(sharedViewModel.lat.value!!, sharedViewModel.lon.value!!)
            }
        })

        sharedViewModel.lat.observe(this,Observer{
            toast("$it")
        })
    }

    private fun loadData(lat : Double, lon : Double) {
        val todayData = viewModel.getTodayByCoord(lat, lon)
        todayData.observe(this, Observer { resource ->
            mBinding.today = resource.data
            mBinding.todayResources = resource
            toast("${resource.data?.cityName}")
        })
    }

    override fun onClick() {

    }
}