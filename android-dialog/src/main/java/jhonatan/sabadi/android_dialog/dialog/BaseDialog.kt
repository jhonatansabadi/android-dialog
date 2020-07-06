package jhonatan.sabadi.android_dialog.dialog

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import jhonatan.sabadi.android_dialog.R
import kotlinx.android.synthetic.main.base_dialog.view.*
import kotlinx.android.synthetic.main.base_dialog_actions.view.*
import kotlinx.android.synthetic.main.simple_dialog.view.*
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.textColor

open class BaseDialog(
    val activity: Activity,
    val layoutId: Int
) : AlertDialog.Builder(activity) {
    protected lateinit var customView: View
    protected lateinit var dialog: AlertDialog

    init {
        setCustomView()
        setDialog()
    }

    fun setCustomView() {
        customView = activity.layoutInflater.inflate(
            layoutId,
            null
        )
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
        get() = AnkoInternals.noGetter()
        set(value) {
            customView.titleDialog?.text = value
        }

    fun setTitleStyle(
        title: String = activity.getString(R.string.title_dialog),
        italic: Boolean = false,
        size: Int = customView.titleDialog.textSize.toInt(),
        color: Int = R.color.black
    ) {
        customView.titleDialog?.apply {
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
        get() = customView.titleDialog.textSize.toInt()
        set(value) {
            customView.titleDialog?.setTextSize(TypedValue.COMPLEX_UNIT_SP, value.toFloat())
        }

    var titleColor = ContextCompat.getColor(activity, R.color.black)
        set(value) {
            customView.titleDialog?.textColor = ContextCompat.getColor(activity, value)
        }

    var content: String
        get() = customView?.contentDialog?.text.toString()
        set(value) {
            customView?.contentDialog?.text = value
        }

    var contentFontSize: Int
        get() = customView.titleDialog.textSize.toInt()
        set(value) {
            customView.contentDialog?.setTextSize(TypedValue.COMPLEX_UNIT_SP, value.toFloat())
        }

    var contentColor = ContextCompat.getColor(activity, R.color.black)
        set(value) {
            customView.contentDialog?.textColor = value
        }

    fun setContentStyle(
        content: String = customView.titleDialog?.text.toString(),
        italic: Boolean = false,
        size: Int = customView.contentDialog.textSize.toInt(),
        color: Int = R.color.black
    ) {

        customView.contentDialog.apply {
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
        height: Int = customView.imageDialog.height,
        width: Int = customView.imageDialog.width
    ) {
        showImage = true
        Glide.with(activity)
            .load(image)
            .into(customView.imageDialog)
    }

    fun actionButton(title: String = "YES", callback: (dialog: AlertDialog) -> Unit) {
        showBothButtons()
        customView.actionButtonDialog?.text = title.toUpperCase()
        customView.actionButtonDialog?.setOnClickListener {
            callback(dialog)
        }
    }


    fun neutralButton(title: String = "NO", callback: (dialog: AlertDialog) -> Unit) {
        showBothButtons()
        customView.neutralButtonDialog?.text = title.toUpperCase()
        customView.neutralButtonDialog?.setOnClickListener {
            callback(dialog)
        }
    }

    fun okButton(callback: (dialog: AlertDialog) -> Unit) {
        showOnlyOkButton()
        customView.okButtonDialog?.apply {
            text = activity.getString(android.R.string.ok)
            setOnClickListener {
                callback(dialog)
            }
        }
    }

    private fun showBothButtons() {
        customView.groupActinButtonDialog?.visibility = View.VISIBLE
        customView.okButtonDialog?.visibility = View.GONE
    }

    private fun showOnlyOkButton() {
        customView.groupActinButtonDialog?.visibility = View.GONE
        customView.okButtonDialog?.visibility = View.VISIBLE
    }

    var actionButtonTextColor = R.color.black
        set(value) {
            customView.actionButtonDialog?.textColor = ContextCompat.getColor(activity, value)
        }

    var neutralButtonTextColor = R.color.black
        set(value) {
            customView.neutralButtonDialog?.textColor = ContextCompat.getColor(activity, value)
        }

    private var showImage: Boolean
        get() = false
        set(value) {
            if (value) {
                customView.imageDialog?.visibility = View.VISIBLE
            }
        }
}