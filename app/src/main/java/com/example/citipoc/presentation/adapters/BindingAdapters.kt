package com.example.citipoc.presentation.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, s: String?) {
    if (!s.isNullOrEmpty()) {
        val glide = Glide.with(view.context)
            .load(s)
            .transition(DrawableTransitionOptions.withCrossFade())
        glide.into(view)
    }
}
