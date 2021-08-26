package jhonatan.sabadi.android_dialog.dialog

import android.app.Activity
import android.app.AlertDialog
import android.text.InputType
import android.text.method.TransformationMethod
import android.view.View
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.textfield.TextInputEditText
import jhonatan.sabadi.android_dialog.R
import kotlinx.android.synthetic.main.base_dialog_actions.view.*
import kotlinx.android.synthetic.main.edit_text_dialog.view.*
import kotlinx.android.synthetic.main.lottie_dialog.view.*
import java.util.*

class EditTextDialog(
    activity: Activity
) : BaseDialog(activity, R.layout.edit_text_dialog) {

    fun setInputType(inputType: Int) {
        customView.inputText.inputType = inputType
    }

    var hint: String
        get() = ""
        set(value) {
            customView.inputTextLayout.hint = value
        }

    fun resultActionButton(
        title: String = "YES",
        callback: (dialog: AlertDialog, result: String) -> Unit
    ) {
        showBothButtons()
        customView.actionButtonDialog?.text = title.uppercase(Locale.getDefault())
        customView.actionButtonDialog?.setOnClickListener {
            dialog.dismiss()
            callback(dialog, customView.inputText.text.toString())
        }
    }
}

fun Activity.editTextDialog(init: EditTextDialog.() -> Unit) {
    EditTextDialog(this).apply(init)
}