package com.example.demo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.demo.R
import com.example.demo.ui.base.BaseBindingActivity

class Utility {
    companion object {

        //------load image in imageview------
        fun loadImage(view: ImageView, imageUrl: String?) {
            Glide.with(view.context)
                .load(imageUrl)
                .apply(RequestOptions().override(200, 200))
                .placeholder(setCircularProgress(view))
                .into(view)
        }

        //-------show progress while loading image in imageview-----------
        fun setCircularProgress(view: ImageView): CircularProgressDrawable {
            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.setColorSchemeColors(
                ContextCompat.getColor(view.context, R.color.purple_200),
                ContextCompat.getColor(view.context, R.color.teal_200),
                ContextCompat.getColor(view.context, R.color.purple_700),
                ContextCompat.getColor(view.context, R.color.teal_700),
            )
            circularProgressDrawable.start()
            return circularProgressDrawable
        }

        //---------connect with ui to load image in imageivew-----------
        @JvmStatic
        @BindingAdapter("image")
        fun loadImageIntoImageView(view: ImageView, ProfilePhoto: String?) {
            if (ProfilePhoto != null) {
                loadImage(view, ProfilePhoto)
            }
        }

        //--------------to check if device online or offline-------
        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                (context as BaseBindingActivity).getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            val isConnected = netInfo != null && netInfo.isConnected
            if (!isConnected) {

            }
            return netInfo != null && netInfo.isConnected
        }
    }
}