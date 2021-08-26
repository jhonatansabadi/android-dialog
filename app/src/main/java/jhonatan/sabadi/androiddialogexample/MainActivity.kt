package jhonatan.sabadi.androiddialogexample

import android.os.Bundle
import android.text.InputType
import android.text.SpannableStringBuilder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import com.android.androiddialog.dialog.colorPickerDialog
import com.android.androiddialog.dialog.lottieDialog
import com.android.androiddialog.dialog.multiItemDialog
import com.android.androiddialog.dialog.simpleDialog
import jhonatan.sabadi.android_dialog.dialog.editTextDialog
import jhonatan.sabadi.androiddialogexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editTextButton.setOnClickListener {
            editTextDialog {
                title = "teste"
                setInputType(InputType.TYPE_CLASS_NUMBER)
                hint = "Digite um número"
                resultActionButton { dialog, result ->
                    Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.simpleDialogButton.setOnClickListener {
            simpleDialog {
                cancelable = false
                title = "Title"
                contentSpannable = SpannableStringBuilder()
                    .append("Teste ")
                    .bold { append(" BOLD") }
                    .append(" texto.")
                okButton {
                    it.dismiss()
                }
            }
        }

        binding.multiItemDialogButton.setOnClickListener {
            multiItemDialog(
                mutableListOf(
                    "Item 1",
                    "Item 1",
                    "Item 1"
                ), R.drawable.ic_alarm
            ) {
                setLottieImage("moto.json")
                title = "Algum titulo"
            }
        }

        binding.lottieSimpleDialogButton.setOnClickListener {
            lottieDialog("moto.json") {
                title = "Moto"
                content = "Moto running so fast..."
                actionButton {

                }
                neutralButton {

                }
            }
        }

        binding.colorPickerDialogButton.setOnClickListener {
            colorPickerDialog(
                mutableListOf(
                    R.color.blue,
                    R.color.red,
                    R.color.red,
                    R.color.red,
                    R.color.red
                ),
            ) {
                setLottieImage("moto.json")
                neutralButton {

                }
                okButton {
                }
            }
        }

    }
}