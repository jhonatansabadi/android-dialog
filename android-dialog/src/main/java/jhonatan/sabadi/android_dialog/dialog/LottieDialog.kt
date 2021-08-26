package com.android.androiddialog.dialog

import android.content.Context
import com.airbnb.lottie.LottieDrawable
import jhonatan.sabadi.android_dialog.R
import jhonatan.sabadi.android_dialog.dialog.BaseDialog


class LottieDialog(
    activity: Context,
    lottieImage: String
) : BaseDialog(activity, R.layout.lottie_dialog) {

    init {
        setImage(lottieImage)
    }

    fun setImage(lottieIMage: String) {
        lottieDialog?.apply {
            setAnimation(lottieIMage)
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
        }
    }
}

fun Context.lottieDialog(lottieImage: String, init: LottieDialog.() -> Unit) {
    LottieDialog(this, lottieImage).apply(init)
}