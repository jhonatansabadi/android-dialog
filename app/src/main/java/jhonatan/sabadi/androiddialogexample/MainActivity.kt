package jhonatan.sabadi.androiddialogexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.androiddialog.dialog.multiItemDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            multiItemDialog(mutableListOf("Item 1", "Item 2"), mutableListOf(R.drawable.ic_alarm, R.drawable.ic_close)) {
                setLottieImage("moto.json")
                title = "Algum titulo"
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