package com.copopapel.coposchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.copopapel.coposchat.adapters.MessageAdapter
import com.copopapel.coposchat.application.CoposChatApplication
import com.copopapel.coposchat.customviews.MessageBoxView
import com.copopapel.coposchat.customviews.SentMessageBoxView
import com.copopapel.coposchat.models.CoposchatContact
import com.copopapel.coposchat.models.CoposchatMessage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var theListForReal:ArrayList<CoposchatMessage>
    private lateinit var adapter: MessageAdapter
    private var contato: CoposchatContact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contato = intent.getParcelableExtra<CoposchatContact>("contact")
        theListForReal = createMessageList()
        adapter = MessageAdapter(theListForReal)

        val theActionBar = supportActionBar
        theActionBar?.setDisplayHomeAsUpEnabled(true)

        if(contato != null){
            val mensagemCorrompida = CoposchatMessage("Mensagem corrompida", "me")
            theActionBar?.title = contato?.name
        }

        val lManager = LinearLayoutManager(this)
        //lManager.reverseLayout = true
        lManager.stackFromEnd = true

        messagePanel.layoutManager = lManager

        messagePanel.adapter = adapter
    }

    fun createMessageList(): ArrayList<CoposchatMessage> {
        val theList = CoposChatApplication.instance.helperContatosDB?.fetchAllMessagesForContact(contato?.id.toString()) ?: return arrayListOf()

        return ArrayList(theList)
    }


    fun addMessage(view:View){
        val message = CoposchatMessage(editText1.text.toString(),"me")
        theListForReal.add(message)
        editText1.setText("")
        CoposChatApplication.instance.helperContatosDB?.addMessage(contato?.id.toString(), message)

        val lastIndexOfTheListForReal = theListForReal.lastIndex

        adapter.notifyItemInserted(lastIndexOfTheListForReal)
        messagePanel.scrollToPosition(lastIndexOfTheListForReal)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}