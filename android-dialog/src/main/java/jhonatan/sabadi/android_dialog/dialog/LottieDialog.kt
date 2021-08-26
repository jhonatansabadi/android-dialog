package com.android.androiddialog.dialog

import android.app.Activity
import com.airbnb.lottie.LottieDrawable
import jhonatan.sabadi.android_dialog.R
import jhonatan.sabadi.android_dialog.dialog.BaseDialog
import kotlinx.android.synthetic.main.lottie_dialog.view.*

class LottieDialog(
    activity: Activity,
    lottieImage: String
) : BaseDialog(activity, R.layout.lottie_dialog) {

    init {
        setImage(lottieImage)
    }

    fun setImage(lottieIMage: String) {
        customView.lottieImage.apply {
            setAnimation(lottieIMage)
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
        }
    }
}

fun Activity.lottieDialog(lottieImage: String, init: LottieDialog.() -> Unit) {
    LottieDialog(this, lottieImage).apply(init)
}