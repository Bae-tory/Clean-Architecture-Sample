package com.sungjae.portfolio.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.sungjae.portfolio.R

@BindingAdapter("setImgUrl")
fun ImageView.setImgUrl(imgUrl: String?) {
    imgUrl?.let {
        Picasso.get()
            .load(imgUrl)
            .placeholder(R.drawable.error)
            .centerCrop()
            .error(R.drawable.error)
            .fit()
            .into(this)
    }
}

@BindingAdapter("setImgUrlCallback", "bindVmForImageCallBack")
fun ImageView.setImgUrlCallBack(imgUrl: String?, vm: Any?) {
    if (vm == null) {
        return
    }
    imgUrl?.let {
        Picasso.get()
            .load(imgUrl)
            .placeholder(R.drawable.error)
            .centerCrop()
            .error(R.drawable.error)
            .fit()
            .into(this, object : Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {
                }
            })
    }
}
