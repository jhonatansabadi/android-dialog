package com.android.androiddialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.androiddialog.interfaces.OnRecyclerClickListener
import kotlinx.android.synthetic.main.android_dialog.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Android Dialog"
    }

    fun simpleDialog(view: View) {
        AndroidDialog(this).apply {
            title = "Title"
            content = "Content"
            yesButton { toast("YES") }
            noButton { toast("NO") }
        }
    }

    fun imageViewDialog(view: View) {
        AndroidDialog(this).apply {
            title = "Title"
            content = "Content"
            setImage(R.drawable.dialog_image)
            yesButton { toast("YES") }
            noButton { toast("NO") }
        }
    }

    fun gifDialog(view: View) {
        AndroidDialog(this).apply {
            title = "Title"
            content = "Content"
            setImage(R.drawable.delete)
            yesButton { toast("YES") }
            noButton { toast("NO") }
        }
    }

    fun custom1Dialog(view: View) {
        AndroidDialog(this).apply {
            title = "Title"
            titleColor = R.color.red
            titleFontSize = 28
            content = "Content"
            setImage(R.drawable.dialog_image)
            yesButton { toast("YES") }
            noButton { toast("NO") }
        }
    }

    fun custom2Dialog(view: View) {
        AndroidDialog(this).apply {
            setTitleStyle(
                title = "Title",
                italic = true,
                color = R.color.red,
                size = 22
            )
            content = "Content"
            setImage(R.drawable.dialog_image)
            yesButton { toast("YES") }
            yesButtonTextColor = R.color.yellow
            noButton { toast("NO") }
        }
    }

    fun custom3Dialog(view: View) {
        val itens = mutableListOf(
            "Apenas uma vez",
            "Diariamente",
            "Semanalmente",
            "Personalizar..."
        )
        MultiItemDialog(this, itens).apply {
            setImage(R.drawable.notification_gif)
            title = "Título qualquer"
            onItemClickListener { value, position ->
                toast("value: $value / position: $position")
            }
        }
//        AndroidDialog(this).apply {
//            title = "Title"
//            setContentStyle(
//                content = "Content",
//                italic = true,
//                color = R.color.green,
//                size = 22
//            )
//            yesButton { toast("YES") }
//            noButton { toast("NO") }
//            noButtonTextColor = R.color.blue
//        }
    }

}
