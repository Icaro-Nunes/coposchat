package com.copopapel.coposchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.copopapel.coposchat.adapters.ContactsAdapter
import com.copopapel.coposchat.models.CoposchatContact
import com.copopapel.coposchat.models.CoposchatMessage
import kotlinx.android.synthetic.main.activity_contacts.*

class ActivityContacts : AppCompatActivity() {
    private val contactList = generateContactList()
    private val contactsAdapter = ContactsAdapter(contactList)
    private val TAG = "ContactsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        contacts_panel.layoutManager = LinearLayoutManager(this)
        contacts_panel.adapter = contactsAdapter

    }

    fun generateContactList(): ArrayList<CoposchatContact>{
        val contato1 = CoposchatContact("Joao", CoposchatMessage("Que bom man","Joao"))
        val contato2 = CoposchatContact("Mestre dos Magos", CoposchatMessage("Mestre?","me"))
        val contato3 = CoposchatContact("Mãe", CoposchatMessage("Entendeu??","Mãe"))
        val contato4 = CoposchatContact("Internet", CoposchatMessage("N ta funcionando","me"))

        return ArrayList<CoposchatContact>(mutableListOf(contato1,contato2,contato3,contato4))
    }

    fun contactClicked(view:View){
        Log.d(TAG,view.id.toString())
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("contact", contactList[view.tag as Int])

        startActivity(intent)
    }
}