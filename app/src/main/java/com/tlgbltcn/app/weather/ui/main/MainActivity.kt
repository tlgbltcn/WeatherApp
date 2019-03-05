package com.tlgbltcn.app.weather.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.material.tabs.TabLayout
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.AppConstant
import com.tlgbltcn.app.weather.R
import com.tlgbltcn.app.weather.core.BaseActivity
import com.tlgbltcn.app.weather.databinding.ActivityMainBinding
import com.tlgbltcn.app.weather.service.WeatherService
import com.tlgbltcn.app.weather.ui.setting.SettingActivity
import com.tlgbltcn.app.weather.utils.extensions.logI
import com.tlgbltcn.app.weather.utils.location.LocationHandler
import com.tlgbltcn.app.weather.utils.service.MyJobService
import timber.log.Timber
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(MainActivityViewModel::class.java),
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    @Inject
    lateinit var api: WeatherService

    protected val TAG = "MainActivity_Location"
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mCurrentLocation: Location? = null
    private var mLastUpdateTime: String? = null
    private var mRequestingLocationUpdates: Boolean = false
    private lateinit var sharedViewModel : SharedViewModel
    private lateinit var locationHandler : LocationHandler
    private lateinit var jobSchedular: JobScheduler


    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes() = R.layout.activity_main

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.inject(this)
        binding.viewModel = viewModel
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        viewModel.initSharedPreferences()
        subscribeUI(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun subscribeUI(savedInstanceState: Bundle?) {
        initToolbar()
        initNavDrawer()
        initTabLayout()
        updateValuesFromBundle(savedInstanceState)
        buildGoogleApiClient()
        setupJobService()
        startUpdate()
    }

    @SuppressLint("ServiceCast")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupJobService() {
        val componentName = ComponentName(this, MyJobService::class.java)
        jobSchedular = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        var jobInfo = JobInfo.Builder(1000, componentName)
                .setMinimumLatency(0)
                .build()

        jobSchedular.let {
            it.schedule(jobInfo)
            Timber.i("JobSchedular is started from activity}")

        }
    }


    fun getLatLon(locationHandler: LocationHandler) {
        this.locationHandler = locationHandler
    }

    private fun isPlayServicesAvailable(context: Context): Boolean {
        val resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
        if (resultCode != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance().getErrorDialog(context as Activity, resultCode, 2).show()
            return false
        }
        return true
    }

    private fun startUpdate() {

        if (!isPlayServicesAvailable(this)) return
        if (!mRequestingLocationUpdates) {
            mRequestingLocationUpdates = true
        } else {
            return
        }
        if (Build.VERSION.SDK_INT < 23) {
            startLocationUpdates()
            return
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) === PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), AppConstant.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun updateValuesFromBundle(savedInstanceState: Bundle?) {
        Timber.i("Updating values from bundle")
        if (savedInstanceState != null) {
            if (savedInstanceState.keySet().contains(AppConstant.REQUESTING_LOCATION_UPDATES_KEY)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(AppConstant.REQUESTING_LOCATION_UPDATES_KEY)
            }

            if (savedInstanceState.keySet().contains(AppConstant.LOCATION_KEY)) {
                mCurrentLocation = savedInstanceState.getParcelable(AppConstant.LOCATION_KEY)
            }
            if (savedInstanceState.keySet().contains(AppConstant.LAST_UPDATED_TIME_STRING_KEY)) {
                mLastUpdateTime = savedInstanceState.getString(AppConstant.LAST_UPDATED_TIME_STRING_KEY)
            }
            updateUI()
        }
    }

    private fun buildGoogleApiClient() {
        logI(TAG + "Build_Google_Api_Client")

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
        createLocationRequest()

    }

    private fun updateUI() {
        mCurrentLocation?.let {
            locationHandler.onLocation(mCurrentLocation!!.latitude, mCurrentLocation!!.longitude)
        }
    }


    override fun onConnected(p0: Bundle?) {
        Timber.i("onConnected")
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED) {
            return
        }
        if (mCurrentLocation == null) {
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
            mLastUpdateTime = DateFormat.getTimeInstance().format(Date())
            updateUI()
        }

        if (mRequestingLocationUpdates!!) {
            startLocationUpdates()
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        Timber.i("Connection suspended")
        mGoogleApiClient?.connect()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Timber.i("Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.errorCode)
    }

    override fun onLocationChanged(location: Location?) {
        Timber.i("onLocationChanged")
        mCurrentLocation = location
        mLastUpdateTime = DateFormat.getTimeInstance().format(Date())
        updateUI()
        stopLocationUpdates()
    }

    protected fun createLocationRequest() {
        mLocationRequest = LocationRequest()
        mLocationRequest?.interval = AppConstant.UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest?.fastestInterval = AppConstant.FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }


    private fun initToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    private fun initNavDrawer() {
        val toogle = object : ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolBar, R.string.nav_open, R.string.nav_close) {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                hideKeyboard(drawerView, this@MainActivity)
            }
        }

        binding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.settings -> {
                    val intent = Intent(this, SettingActivity::class.java)
                    startActivity(intent)
                }


            }
            true
        }

        binding.navView.itemIconTintList = null
    }

    private fun initTabLayout() {
        binding.viewPager.adapter = MainTabAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)
        binding.viewPager.offscreenPageLimit = 3
        binding.tabLayout.setSelectedTabIndicator(R.drawable.cat_tabs_rounded_line_indicator)
        lateinit var tab: TabLayout.Tab

        val tabTitle: List<Int> = listOf(R.string.today, R.string.fivedays, R.string.fifteendays)
        tabTitle.withIndex().forEach {
            tab = binding.tabLayout.getTabAt(it.index)!!
            with(tab) {
                text = getString(it.value)
                tag = getString(it.value)
            }
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
                if (p0?.tag != null) {
                    updateTitle(p0.position)
                }

            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0?.tag != null) {
                    binding.viewPager.currentItem = p0.position
                    if (currentFocus != null) {
                        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                    }
                }
            }

        })

    }

    private fun updateTitle(position: Int) {
        binding.toolBar.title = binding.tabLayout.getTabAt(position)!!.tag.toString()
    }

    fun hideKeyboard(pView: View, pActivity: Activity) {
        var view = pView
        if (pActivity.window.currentFocus != null) {
            view = pActivity.window.currentFocus
            val imm = pActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(binding.navView)) {
            binding.drawerLayout.closeDrawer(binding.navView)
        } else {
            super.onBackPressed()
        }
    }


    private fun startLocationUpdates() {
        Timber.i("startLocationUpdates")

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest!!)
        val result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build())
        result.setResultCallback { locationSettingsResult ->
            val status = locationSettingsResult.status

            when (status.statusCode) {
                LocationSettingsStatusCodes.SUCCESS ->
                    if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION) === PackageManager.PERMISSION_GRANTED) {
                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this@MainActivity)
                    }
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                    try {
                        status.startResolutionForResult(this@MainActivity, AppConstant.REQUEST_CHECK_SETTINGS)
                    } catch (e: IntentSender.SendIntentException) {
                    }

                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                }
            }
        }
    }


    private fun showRationaleDialog() {
        AlertDialog.Builder(this)
                .setPositiveButton("Give Permission") { dialog, which ->
                    ActivityCompat.requestPermissions(this@MainActivity,
                            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), AppConstant.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
                }
                .setNegativeButton("Deny") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Location information permission is not allowed.", Toast.LENGTH_SHORT).show()
                    mRequestingLocationUpdates = false
                }
                .setCancelable(false)
                .setMessage("This application needs to allow use of location information.")
                .show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            AppConstant.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates()
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                        mRequestingLocationUpdates = false
                        Toast.makeText(this@MainActivity, "To enable the function of this application," +
                                " please activate the application's setting screen from the terminal's setting screen.", Toast.LENGTH_SHORT).show()
                    } else {
                        showRationaleDialog()
                    }
                }
            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            AppConstant.REQUEST_CHECK_SETTINGS -> when (resultCode) {
                Activity.RESULT_OK -> startLocationUpdates()
                Activity.RESULT_CANCELED -> {
                }
            }
        }

    }


    override fun onStart() {
        super.onStart()
        mGoogleApiClient?.connect()
    }

    override fun onResume() {
        super.onResume()
        isPlayServicesAvailable(this)

        if (mGoogleApiClient!!.isConnected && mRequestingLocationUpdates) {
            startLocationUpdates()
        }
    }


    override fun onPause() {
        super.onPause()
        // Stop location updates to save battery, but don't disconnect the GoogleApiClient object.
        if (mGoogleApiClient!!.isConnected()) {
            stopLocationUpdates()
        }
    }

    protected fun stopLocationUpdates() {
        Timber.i("stopLocationUpdates")
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this)
    }


    override fun onStop() {
        stopLocationUpdates()
        mGoogleApiClient?.disconnect()
        super.onStop()
    }
}
