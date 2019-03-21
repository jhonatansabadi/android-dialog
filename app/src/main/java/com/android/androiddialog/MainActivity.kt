package com.android.androiddialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.androiddialog.dialog.SimpleDialog
import com.android.androiddialog.dialog.MultiItemDialog
import com.android.androiddialog.dialog.simpleDialog
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Android Dialog"
    }

    fun simpleDialog(view: View) {
        simpleDialog {
            title = "Jhonatan"
            content = "Sabadi"
        }
//        SimpleDialog(this).apply {
//            title = "Title"
//            content = "Content"
//            yesButton { toast("YES") }
//            noButton { toast("NO") }
//        }
    }

    fun imageViewDialog(view: View) {
        SimpleDialog(this).apply {
            title = "Title"
            content = "Content"
            setImage(R.drawable.person)
            yesButton { toast("YES") }
            noButton { toast("NO") }
        }
    }

    fun gifDialog(view: View) {
        SimpleDialog(this).apply {
            title = "Title"
            content = "Content"
            setImage(R.drawable.delete)
            yesButton { toast("YES") }
            noButton { toast("NO") }
        }
    }

    fun custom1Dialog(view: View) {
        SimpleDialog(this).apply {
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
        SimpleDialog(this).apply {
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
        SimpleDialog(this).apply {
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
        val itens = mutableListOf(
            "Item 0",
            "Item 1"
        )
        val icons = mutableListOf(
            R.drawable.run,
            R.drawable.walk
        )
        MultiItemDialog(this, itens, icons).apply {
            title = "Itens"
            titleColor = R.color.red
            onItemClickListener { value, position ->
                toast("value: $value / position: $position")
            }
        }
    }

}
