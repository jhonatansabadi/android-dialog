package jhonatan.sabadi.androiddialogexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.androiddialog.dialog.colorPickerDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            colorPickerDialog(mutableListOf(R.color.colorAccent)) {
                onColorClickListener { color, position ->
                    Toast.makeText(this@MainActivity, color.toString(), Toast.LENGTH_LONG)
                }
            }
        }
    }
}