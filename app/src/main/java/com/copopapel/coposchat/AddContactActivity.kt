package com.copopapel.coposchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.copopapel.coposchat.application.CoposChatApplication
import kotlinx.android.synthetic.main.activity_add_contact.*
import java.lang.Exception

class AddContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val theActionBar = supportActionBar

        theActionBar?.title = resources.getString(R.string.addContactActivityTitle)
        theActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onClickSaveContact(view:View){
        val input = newContactEDT.text.toString()

        if(!(input.isBlank())){
            try {
                CoposChatApplication.instance.helperContatosDB?.addContato(input)
                finish()
            } catch (ex: Exception){
                ex.printStackTrace()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}