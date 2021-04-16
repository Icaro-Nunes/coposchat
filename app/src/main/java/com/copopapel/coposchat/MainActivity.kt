package com.copopapel.coposchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.copopapel.coposchat.customviews.MessageBoxView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addMessage(view:View){
        val newMessageBoxView =
            MessageBoxView(
                this,
                textView.text.toString()
            )
        messageWindow.addView(newMessageBoxView)
        textView.setText("")
    }
}