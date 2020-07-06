package jhonatan.sabadi.androiddialogexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.androiddialog.dialog.colorPickerDialog
import com.android.androiddialog.dialog.lottieDialog
import com.android.androiddialog.dialog.simpleDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            colorPickerDialog(mutableListOf(R.color.black, R.color.blue)) {
                okButton {
                    it.dismiss()
                    simpleDialog {
                        setImage(R.drawable.ic_launcher_foreground)
                        title = "teste"
                    }
                }
            }
//            colorPickerDialog(mutableListOf(Color.BLUE, Color.BLACK))  {
//                title = "Teste"
//                content = "Test"
//                actionButton("Save") {
//                    it.dismiss()
//                    Toast.makeText(this@MainActivity, "saved", Toast.LENGTH_SHORT).show()
//                }
//                neutralButton("cancel") {
//                    it.dismiss()
//                }
//            }
        }
    }
}