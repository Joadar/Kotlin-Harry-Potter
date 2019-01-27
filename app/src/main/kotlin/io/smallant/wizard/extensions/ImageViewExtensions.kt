package io.smallant.wizard.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.smallant.wizard.utils.glide.GlideApp

@BindingAdapter("android:src")
fun ImageView.setImageUrl(url: String?) {
    GlideApp.with(context)
        .load(url)
        .apply(RequestOptions.skipMemoryCacheOf(false))
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
        .into(this)
}