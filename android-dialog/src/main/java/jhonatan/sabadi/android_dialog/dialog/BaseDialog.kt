package jhonatan.sabadi.android_dialog.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import jhonatan.sabadi.android_dialog.R

open class BaseDialog(
    val activity: Context,
    val layoutId: Int,
) : AlertDialog.Builder(activity) {
    protected lateinit var customView: View
    protected lateinit var dialog: AlertDialog
    protected var titleDialog: TextView? = null
    protected var contentDialog: TextView? = null
    protected var imageDialog: ImageView? = null
    protected var lottieDialog: LottieAnimationView? = null
    protected lateinit var actionButtonDialog: MaterialButton
    protected lateinit var neutralButtonDialog: MaterialButton
    protected lateinit var okButtonDialog: MaterialButton
    protected lateinit var groupActinButtonDialog: Group
    protected var recyclerViewDialog: RecyclerView? = null

    init {
        setCustomView()
        setDialog()
    }

    fun setCustomView() {
        val layoutInflater = LayoutInflater.from(activity)
        customView = layoutInflater.inflate(
            layoutId,
            null
        )
        titleDialog = customView.findViewById(R.id.titleDialog)
        contentDialog = customView.findViewById(R.id.contentDialog)
        imageDialog = customView.findViewById(R.id.imageDialog)
        lottieDialog = customView.findViewById(R.id.lottieDialog)
        actionButtonDialog = customView.findViewById(R.id.actionButtonDialog)
        neutralButtonDialog = customView.findViewById(R.id.neutralButtonDialog)
        okButtonDialog = customView.findViewById(R.id.okButtonDialog)
        groupActinButtonDialog = customView.findViewById(R.id.groupActinButtonDialog)
        recyclerViewDialog = customView.findViewById(R.id.recyclerViewDialog)
        this.setView(customView)
    }

    fun setDialog() {
        dialog = this
            .create()
            .apply {
                show()
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }

    var cancelable: Boolean
        get() = true
        set(value) {
            dialog.setCancelable(value)
        }

    open var title: String
        get() = ""
        set(value) {
            titleDialog?.text = value
        }

    fun setTitleStyle(
        title: String = activity.getString(R.string.title_dialog),
        italic: Boolean = false,
        size: Int = titleDialog?.textSize?.toInt() ?: 0,
        color: Int = R.color.black
    ) {
        titleDialog?.apply {
            if (text.toString().isNotEmpty()) {
                text = title
            }
            if (italic) {
                setTypeface(typeface, Typeface.ITALIC)
            }
            setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
            setTextColor(ContextCompat.getColor(activity, color))
        }
    }

    var titleFontSize: Int
        get() = titleDialog?.textSize?.toInt() ?: 0
        set(value) {
            titleDialog?.setTextSize(TypedValue.COMPLEX_UNIT_SP, value.toFloat())
        }

    var titleColor = ContextCompat.getColor(activity, R.color.black)
        set(value) {
            titleDialog?.setTextColor(ContextCompat.getColor(activity, value))
        }

    var content: String
        get() = contentDialog?.text.toString()
        set(value) {
            contentDialog?.apply {
                visibility = View.VISIBLE
                text = value
            }
        }

    var contentSpannable
        get(): CharSequence = contentDialog?.text.toString()
        set(value) {
            contentDialog?.apply {
                visibility = View.VISIBLE
                text = value
            }
        }

    var contentFontSize: Int
        get() = titleDialog?.textSize?.toInt() ?: 0
        set(value) {
            contentDialog?.setTextSize(TypedValue.COMPLEX_UNIT_SP, value.toFloat())
        }

    var contentColor = ContextCompat.getColor(activity, R.color.black)
        set(value) {
            contentDialog?.setTextColor(value)
        }

    fun setContentStyle(
        content: String = titleDialog?.text.toString(),
        italic: Boolean = false,
        size: Int = contentDialog?.textSize?.toInt() ?: 0,
        color: Int = R.color.black
    ) {

        contentDialog?.apply {
            if (text.toString().isNotEmpty()) {
                text = content
            }
            if (italic) {
                setTypeface(typeface, Typeface.ITALIC)
            }
            setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
            setTextColor(ContextCompat.getColor(activity, color))
        }
    }


    fun setImage(
        image: Int,
        height: Int = imageDialog?.height ?: 0,
        width: Int = imageDialog?.width ?: 0
    ) {
        showImage = true
        imageDialog?.let {
            Glide.with(activity)
                .load(image)
                .into(it)
        }
    }

    fun setLottieImage(lottieIMage: String) {
        showLottieImage = true
        lottieDialog?.apply {
            setAnimation(lottieIMage)
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
        }
    }

    fun actionButton(title: String = "Confirm", callback: (dialog: AlertDialog) -> Unit) {
        showBothButtons()
        actionButtonDialog?.text = title.toUpperCase()
        actionButtonDialog?.setOnClickListener {
            dialog.dismiss()
            callback(dialog)
        }
    }


    fun neutralButton(title: String = "Cancel", callback: (dialog: AlertDialog) -> Unit) {
        showBothButtons()
        neutralButtonDialog?.text = title.toUpperCase()
        neutralButtonDialog?.setOnClickListener {
            dialog.dismiss()
            callback(dialog)
        }
    }

    fun okButton(callback: (dialog: AlertDialog) -> Unit) {
        showOnlyOkButton()
        okButtonDialog?.apply {
            text = activity.getString(android.R.string.ok)
            setOnClickListener {
                dialog.dismiss()
                callback(dialog)
            }
        }
    }

    internal fun showBothButtons() {
        groupActinButtonDialog?.visibility = View.VISIBLE
        okButtonDialog?.visibility = View.GONE
    }

    private fun showOnlyOkButton() {
        groupActinButtonDialog?.visibility = View.GONE
        okButtonDialog?.visibility = View.VISIBLE
    }

    var actionButtonTextColor = R.attr.colorControlNormal
        set(value) {
            actionButtonDialog?.setTextColor(ContextCompat.getColor(activity, value))
        }

    var neutralButtonTextColor = R.attr.colorControlNormal
        set(value) {
            neutralButtonDialog?.setTextColor(ContextCompat.getColor(activity, value))
        }

    fun setOkButtonIcon(iconRes: Int, color: Int? = null) {
        okButtonDialog.apply {
            icon = activity.getDrawable(iconRes)
            color?.let {
                iconTint = ColorStateList.valueOf(ContextCompat.getColor(activity, color))
            }
        }
    }

    fun setActionButtonIcon(iconRes: Int, color: Int? = null) {
        actionButtonDialog.apply {
            icon = activity.getDrawable(iconRes)
            color?.let {
                iconTint = ColorStateList.valueOf(ContextCompat.getColor(activity, color))
            }
        }
    }

    fun setNeutralButtonIcon(iconRes: Int, color: Int? = null) {
        neutralButtonDialog.apply {
            icon = activity.getDrawable(iconRes)
            color?.let {
                iconTint = ColorStateList.valueOf(ContextCompat.getColor(activity, color))
            }
        }
    }

    private var showImage: Boolean
        get() = false
        set(value) {
            if (value) {
                imageDialog?.visibility = View.VISIBLE
            }
        }

    private var showLottieImage: Boolean
        get() = false
        set(value) {
            if (value) {
                lottieDialog?.visibility = View.VISIBLE
            }
        }
}