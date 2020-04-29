package jhonatan.sabadi.androiddialogexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.androiddialog.dialog.colorPickerDialog
import com.android.androiddialog.dialog.multiItemDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            colorPickerDialog(mutableListOf(R.color.black, R.color.blue)) {
                okButton { color, position ->

                }
            }
        }
    }
}