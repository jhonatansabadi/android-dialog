package jhonatan.sabadi.android_dialog.dialog

import android.app.Activity
import android.text.InputType
import android.text.method.TransformationMethod
import android.view.View
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.textfield.TextInputEditText
import jhonatan.sabadi.android_dialog.R
import kotlinx.android.synthetic.main.edit_text_dialog.view.*
import kotlinx.android.synthetic.main.lottie_dialog.view.*

class EditTextDialog(
        activity: Activity
) : BaseDialog(activity, R.layout.edit_text_dialog) {

//
//    fun setInputType(inputType: Int) {
//        customView.inputText.inputType = inputType
//    }
//
//    var hint: String
//        get() = ""
//        set(value) {
//            customView.inputText.hint = value
//        }

    var customInputText: View = customView.inputText.rootView

}

fun Activity.editTextDialog(init: EditTextDialog.() -> Unit) {
    EditTextDialog(this).apply(init)
}