package com.tlgbltcn.app.weather.utils.provider

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Looper
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.okButton

object LocationService {

    @SuppressLint("StaticFieldLeak")
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            doAsync {
                location = locationResult.lastLocation
                onSuccess(location)
            }
        }
    }
    private lateinit var onSuccess: (location : Location) -> Unit
    private lateinit var onError: () -> Unit
    lateinit var location: Location

    fun init(activity: Activity) {
        fusedLocationProviderClient = FusedLocationProviderClient(activity)
        locationRequest = LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(1000).setFastestInterval(1000).setNumUpdates(1)
    }

    private fun checkLocationStatusAndGetLocation(activity: Activity) {
        doAsync {
            when {
                ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> LocationServices.getSettingsClient(activity).checkLocationSettings(LocationSettingsRequest.Builder().addLocationRequest(locationRequest).setAlwaysShow(true).build()).addOnCompleteListener { task ->
                    doAsync {
                        try {
                            task.getResult(ApiException::class.java)
                            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
                        } catch (exception: ApiException) {
                            when (exception.statusCode) {
                                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                                    try {
                                        (exception as ResolvableApiException).startResolutionForResult(activity, 7025)
                                    } catch (ex: Exception) {
                                        promptShowLocation(activity)
                                    }
                                }
                                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                                    promptShowLocation(activity)
                                }
                            }
                        }
                    }
                }
                ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION) -> activity.runOnUiThread {
                    activity.alert("To continue, allow the device to use location, witch uses Google's Location Service") {
                        okButton {
                            val ite = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", activity.packageName, null))
                            ite.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            activity.startActivity(ite)
                            onError()
                        }
                        negativeButton("Cancelar", { onError() })
                        onCancelled { onError() }
                    }.show()
                }
                else -> ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 7024)
            }
        }
    }

    private fun promptShowLocation(activity: Activity) {
        activity.runOnUiThread {
            activity.alert("To continue, allow the device to use location, witch uses Google's Location Service") {
                okButton {
                    activity.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    onError()
                }
                negativeButton("Cancelar", { onError() })
                onCancelled { onError() }
            }.show()
        }
    }

    fun onRequestPermissionsResult(activity: Activity, requestCode: Int, grantResults: IntArray) {
        if (requestCode == 7024) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkLocationStatusAndGetLocation(activity)
            } else {
                onError()
            }
        }
    }

    fun onActivityResult(activity: Activity, requestCode: Int, resultCode: Int) {
        if (requestCode == 7025) {
            if (resultCode == Activity.RESULT_OK) {
                checkLocationStatusAndGetLocation(activity)
            } else {
                onError()
            }
        }
    }


}