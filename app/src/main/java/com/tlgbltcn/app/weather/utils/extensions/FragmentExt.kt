package com.tlgbltcn.app.weather.utils.extensions

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun androidx.fragment.app.Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = activity?.toast(message, duration)

inline fun androidx.fragment.app.Fragment.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder) = activity?.alertDialog(body)


inline fun <T> LiveData<T>.reobserve(owner: LifecycleOwner, crossinline func: (T?) -> (Unit)) {
    removeObservers(owner)
     observe(owner, Observer<T> { t -> func(t) }) }