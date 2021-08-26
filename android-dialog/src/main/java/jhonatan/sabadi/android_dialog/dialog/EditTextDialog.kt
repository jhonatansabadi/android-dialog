package jhonatan.sabadi.android_dialog.dialog

import android.app.AlertDialog
import android.content.Context
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import jhonatan.sabadi.android_dialog.R
import java.util.*

class EditTextDialog(
    activity: Context
) : BaseDialog(activity, R.layout.edit_text_dialog) {

    private lateinit var inputText: TextInputEditText
    private lateinit var inputTextLayout: TextInputLayout

    init {
        setViews()
    }

    private fun setViews() {
        inputText = customView.findViewById(R.id.inputText)
        inputTextLayout = customView.findViewById(R.id.inputTextLayout)
    }

    fun setInputType(inputType: Int) {
        inputText.inputType = inputType
    }

    var hint: String
        get() = ""
        set(value) {
            inputTextLayout.hint = value
        }

    fun resultActionButton(
        title: String = "YES",
        callback: (dialog: AlertDialog, result: String) -> Unit
    ) {
        showBothButtons()
        actionButtonDialog?.text = title
        actionButtonDialog?.setOnClickListener {
            dialog.dismiss()
            callback(dialog, inputText.text.toString())
        }
    }
}

fun Context.editTextDialog(init: EditTextDialog.() -> Unit) {
    EditTextDialog(this).apply(init)
}