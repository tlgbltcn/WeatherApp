package com.tlgbltcn.app.weather.utils.provider

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.location.LocationListener
import android.location.Criteria
import android.location.LocationManager
import android.location.Location



class SingleShotLocationProvider {


    interface LocationCallback {
        fun onNewLocationAvailable(location: GPSCoordinates)
    }

    @SuppressLint("MissingPermission")
// calls back to calling thread, note this is for low grain: if you want higher precision, swap the
    // contents of the else and if. Also be sure to check gps permission/settings are allowed.
    // call usually takes <10ms
    fun requestSingleUpdate(context: Context, callback: LocationCallback) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (isNetworkEnabled) {
            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_COARSE
            locationManager.requestSingleUpdate(criteria, object  : LocationListener{
                override fun onLocationChanged(p0: Location?) {

                    callback.onNewLocationAvailable(GPSCoordinates(p0?.latitude!!, p0.longitude))

                }

                override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onProviderEnabled(p0: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onProviderDisabled(p0: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            },null)
        } else {
            val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            if (isGPSEnabled) {
                val criteria = Criteria()
                criteria.accuracy = Criteria.ACCURACY_FINE
                locationManager.requestSingleUpdate(criteria, object : LocationListener {
                    override fun onLocationChanged(p0: Location?) {
                        callback.onNewLocationAvailable(GPSCoordinates(p0?.latitude!!, p0.longitude))
                    }

                    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                    override fun onProviderEnabled(provider: String) {}
                    override fun onProviderDisabled(provider: String) {}
                }, null)
            }
        }
    }


    class GPSCoordinates (theLatitude : Double ,theLongitude: Double ) {
        var longitude = theLatitude
        var latitude = theLongitude

    }

}