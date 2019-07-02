package com.android.androiddialog

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.androiddialog.dialog.colorPickerDialog
import com.android.androiddialog.dialog.multiItemDialog
import com.android.androiddialog.dialog.simpleDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.simple_dialog.*
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Android Dialog"
    }

    fun simpleDialog(view: View) {
        simpleDialog {
            title = "Title"
            content = "Content"
            okButton { }
        }
    }

    fun imageViewDialog(view: View) {
        simpleDialog {
            title = "Title"
            content = "Content"
            setImage(R.drawable.person)
            yesButton { toast("YES") }
            noButton { toast("NO") }
        }
    }

    fun gifDialog(view: View) {
        simpleDialog {
            title = "Title"
            content = "Content"
            cancelable = false
            setImage(R.drawable.delete)
            yesButton { toast("YES") }
            noButton { toast("NO") }
        }
    }

    fun custom1Dialog(view: View) {
        simpleDialog {
            title = "Title"
            titleColor = R.color.red
            titleFontSize = 28
            content = "Content"
            setImage(R.drawable.person)
            yesButton { toast("YES") }
            noButton { toast("NO") }
        }
    }

    fun custom2Dialog(view: View) {
        simpleDialog {
            setTitleStyle(
                title = "Title",
                italic = true,
                color = R.color.red,
                size = 22
            )
            content = "Content"
            setImage(R.drawable.person)
            yesButton { toast("YES") }
            yesButtonTextColor = R.color.yellow
            noButton { toast("NO") }
        }
    }

    fun custom3Dialog(view: View) {
        simpleDialog {
            title = "Title"
            setContentStyle(
                content = "Content",
                italic = true,
                color = R.color.green,
                size = 22
            )
            yesButton { toast("YES") }
            noButton { toast("NO") }
            noButtonTextColor = R.color.blue
        }
    }

    fun custom4Dialog(view: View) {
        val itens = mutableListOf<String>()
        for (i in 0..20) {
            itens.add("Item $i")
        }
        val icons = mutableListOf(
            R.drawable.run,
            R.drawable.walk
        )
        multiItemDialog(itens) {
            setImage(R.drawable.person)
            onItemClickListener { value, position ->
                toast("value: $value / position: $position")
            }
        }
    }

    fun custom5Dialog(view: View) {
        val colors = mutableListOf<Int>()
        val rnd = Random()
        for (i in 0..19) {
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            colors.add(color)
        }
        colorPickerDialog(colors) {
            setImage(R.drawable.paint)
            okButton { color, position ->
                button8.setBackgroundColor(color)
            }
        }
    }

}
