package com.tlgbltcn.app.weather.ui.setting

import android.os.Bundle
import android.view.MenuItem
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.R
import com.tlgbltcn.app.weather.core.BaseActivity
import com.tlgbltcn.app.weather.databinding.ActivitySettingBinding
import com.tlgbltcn.app.weather.ui.setting.fragment.SettingsFragment

class SettingActivity : BaseActivity<SettingActivityViewModel, ActivitySettingBinding>(SettingActivityViewModel::class.java){
    override fun getLayoutRes(): Int = R.layout.activity_setting

    override fun initViewModel(viewModel: SettingActivityViewModel) { binding.viewModel = viewModel }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.inject(this)
        subscribeUI()

    }

    private fun subscribeUI() {
        initToolBar()
        initFragment()
    }

    private fun initFragment() {

        fragmentManager.beginTransaction()
                .replace(R.id.container, SettingsFragment())
                .commit()    }




    private fun initToolBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)

    }

}