package com.copopapel.coposchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.copopapel.coposchat.adapters.MessageAdapter
import com.copopapel.coposchat.customviews.MessageBoxView
import com.copopapel.coposchat.customviews.SentMessageBoxView
import com.copopapel.coposchat.models.CoposchatContact
import com.copopapel.coposchat.models.CoposchatMessage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val theListForReal = createMessageList(4)
    private val adapter = MessageAdapter(theListForReal)
    private var contato: CoposchatContact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contato = intent.getParcelableExtra<CoposchatContact>("contact")

        val theActionBar = supportActionBar
        theActionBar?.setDisplayHomeAsUpEnabled(true)

        if(contato != null){
            val mensagemCorrompida = CoposchatMessage("Mensagem corrompida", "me")
            theActionBar?.title = contato?.name
            theListForReal.add(contato?.lastMessage!!)
        }

        val lManager = LinearLayoutManager(this)
        //lManager.reverseLayout = true
        lManager.stackFromEnd = true

        messagePanel.layoutManager = lManager

        messagePanel.adapter = adapter
    }

    fun createMessageList(num: Int): ArrayList<CoposchatMessage> {
        val theList = ArrayList<CoposchatMessage>()

        for(i in 1..num){
            theList.add(CoposchatMessage(i.toString(), "me"))
        }

        return theList
    }


    fun addMessage(view:View){
        theListForReal.add(CoposchatMessage(editText1.text.toString(),"me"))
        editText1.setText("")

        val lastIndexOfTheListForReal = theListForReal.lastIndex

        adapter.notifyItemInserted(lastIndexOfTheListForReal)
        messagePanel.scrollToPosition(lastIndexOfTheListForReal)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}