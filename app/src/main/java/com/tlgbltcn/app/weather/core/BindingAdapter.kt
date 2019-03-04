package com.tlgbltcn.app.weather.core

import android.annotation.SuppressLint
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tlgbltcn.app.weather.R
import com.tlgbltcn.app.weather.core.base.BaseAdapter
import com.tlgbltcn.app.weather.service.Status
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:set_list")
    fun setList(recyclerView: RecyclerView, list: List<Nothing>?) {
        list?.let {
            val adapter = recyclerView.adapter as BaseAdapter<*>
            adapter.submitList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("app:src")
    fun setImageViewResource(imageView: ImageView, resource: Int?) {
        resource?.let { imageView.setImageResource(it) }
    }

    @JvmStatic
    @BindingAdapter("app:setImage")
    fun setImage(view: ImageView, url: String?) {
        url?.isNotEmpty().let {
            Picasso.get()
                    .load(Uri.parse(url))
                    .error(R.drawable.bg_default_image)
                    .fit()
                    .centerCrop()
                    .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("app:progressVisibility")
    fun setMPRogress(progressBar: ProgressBar, status: Status) {
        progressBar.let {
            when (status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    @BindingAdapter("app:unixDateTime")
    fun setDateTime(view: TextView, dt: Long?) {
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
        dt?.let {
            val date = Date(dt * 1000)
            view.text = simpleDateFormat.format(date)
        }

    }


}