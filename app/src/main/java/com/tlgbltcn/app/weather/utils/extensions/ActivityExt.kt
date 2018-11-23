package com.tlgbltcn.app.weather.utils.extensions

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, duration).show()

inline fun Activity.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder): AlertDialog {
    return AlertDialog.Builder(this)
            .body()
            .show()
}


private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()

}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {

    with(supportFragmentManager) {
        transact {
            replace(frameId, fragment)
        }
    }

}

fun AppCompatActivity.replaceFragmentInActivity(fragment : Fragment, frameId : Int){
    with(supportFragmentManager){
        transact {
            replace(frameId, fragment)
        }
    }
}

fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, tag: String) {
    with(supportFragmentManager) {
        transact {
            add(fragment, tag)
        }
    }
}
