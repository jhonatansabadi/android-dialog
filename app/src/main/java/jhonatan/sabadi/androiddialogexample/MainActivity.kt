package jhonatan.sabadi.androiddialogexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.androiddialog.dialog.colorPickerDialog
import com.android.androiddialog.dialog.lottieDialog
import com.android.androiddialog.dialog.multiItemDialog
import com.android.androiddialog.dialog.simpleDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            multiItemDialog(mutableListOf("a", "b"))  {
                title = "Teste"
                content = "Test"
                actionButton("Save") {
                    Toast.makeText(this@MainActivity, "saved", Toast.LENGTH_SHORT).show()
                }
                neutralButton("cancel") {
                    it.dismiss()
                }
            }
        }
    }
}