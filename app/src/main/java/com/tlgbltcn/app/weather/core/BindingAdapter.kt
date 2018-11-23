package com.tlgbltcn.app.weather.core

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tlgbltcn.app.weather.R
import com.tlgbltcn.app.weather.core.base.BaseAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:set_list")
    fun setList(recyclerView : RecyclerView, list : List<Nothing>?){
        list?.let {
            val adapter = recyclerView.adapter as BaseAdapter<*>
            adapter.submitList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("app:src")
    fun setImageViewResource(imageView : ImageView, resource : Int?){
        resource?.let { imageView.setImageResource(it) }
    }

    @JvmStatic
    @BindingAdapter("app:setImage")
    fun setImage(view : ImageView,url : String?){
        url?.isNotEmpty().let {
            Picasso
                    .get()
                    .load(Uri.parse(url))
                    .error(R.drawable.bg_default_image)
                    .fit()
                    .centerCrop()
                    .into(view)
        }

    }



}