package jhonatan.sabadi.androiddialogexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.androiddialog.dialog.colorPickerDialog
import com.android.androiddialog.dialog.lottieDialog
import com.android.androiddialog.dialog.multiItemDialog
import com.android.androiddialog.dialog.simpleDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        simpleDialogButton.setOnClickListener {
            simpleDialog {
                cancelable = false
                title = "Title"
                content = "Content test"
                okButton {
                    it.dismiss()
                }
            }
        }

        multiItemDialogButton.setOnClickListener {
            multiItemDialog(mutableListOf(
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 1",
                    "Item 2"
            ), R.drawable.ic_alarm) {
                setLottieImage("moto.json")
                title = "Algum titulo"
                actionButton("OK") {
                    Toast.makeText(this@MainActivity, "OK", Toast.LENGTH_LONG).show()
                }
                neutralButton {
                    it.dismiss()
                }
            }
        }

        lottieSimpleDialogButton.setOnClickListener {
            lottieDialog("moto.json") {
                title = "Moto"
            }
        }

        colorPickerDialogButton.setOnClickListener {
            colorPickerDialog(mutableListOf(R.color.blue, R.color.red)) {
                okButton {
                    it.dismiss()
                }
            }
        }

    }
}