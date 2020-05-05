package com.android.androiddialog.dialog

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.utils.LottieValueAnimator
import com.bumptech.glide.Glide
import jhonatan.sabadi.android_dialog.R
import jhonatan.sabadi.android_dialog.dialog.BaseDialog
import kotlinx.android.synthetic.main.lottie_dialog.view.*
import kotlinx.android.synthetic.main.simple_dialog.view.*
import kotlinx.android.synthetic.main.simple_dialog.view.contentDialog
import kotlinx.android.synthetic.main.simple_dialog.view.noButtonDialog
import kotlinx.android.synthetic.main.simple_dialog.view.okButtonDialog
import kotlinx.android.synthetic.main.simple_dialog.view.titleDialog
import kotlinx.android.synthetic.main.simple_dialog.view.yesButtonDialog
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.textColor

class LottieDialog(
    activity: Activity,
    lottieImage: String
) : BaseDialog(activity, R.layout.lottie_dialog) {

    init {
        setImage(lottieImage)
    }

    override fun setImage(lottieIMage: String) {
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