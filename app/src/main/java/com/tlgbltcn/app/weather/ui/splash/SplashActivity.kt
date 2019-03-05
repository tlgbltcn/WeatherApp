package com.tlgbltcn.app.weather.ui.splash

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.R
import com.tlgbltcn.app.weather.service.WeatherService
import com.tlgbltcn.app.weather.ui.main.MainActivity
import timber.log.Timber
import java.util.*
import javax.inject.Inject


class SplashActivity : AppCompatActivity() {

    private val REQ_CODE = 100

    private val REQ_CODE_GPS = 101

    private val permissionList = ArrayList<String>()

    private var permissionsGranted = false

    var isGpsOpen: Boolean? = null

    @Inject
    lateinit var api: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        (application as App).component.inject(this)
        startMainActivity()
        //subscribeUI()

    }

    private fun subscribeUI() {
        when {
            Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP -> checkLocationPermission()
            else -> when {
                !isGpsOpen!! -> {
                }
                else -> startMainActivity()
            }
        }
    }

    fun startMainActivity() {
        Thread(Runnable {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                Timber.e(e)
            }
            runOnUiThread {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }).start()
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), REQ_CODE)
        } else {
            startMainActivity()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQ_CODE -> {
                permissionsGranted = grantResults.isNotEmpty() &&
                        checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                        checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            }
        }

        if (permissionsGranted) {

            startMainActivity()
        } else {
            finish()
            //Buraya Search Activity gelecek
        }

    }


    fun getLocationStatusCheck(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    /* private fun buildAlertMessageNoGps() {
         alert("We can't show your position because you generally disabled the location service for your device."){
             yesButton { startActivityForResult(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),REQ_CODE_GPS) }
             noButton { it.cancel() }
         }.show()


     }*/


    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        logE("onActivityForResult..")
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQ_CODE_GPS){
            when(requestCode) {
                REQ_CODE_GPS -> {
                    startMainActivity()
                }
            }

        }
    }*/


}