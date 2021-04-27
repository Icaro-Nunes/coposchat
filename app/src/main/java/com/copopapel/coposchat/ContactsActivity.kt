package com.copopapel.coposchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.copopapel.coposchat.adapters.ContactsAdapter
import com.copopapel.coposchat.application.CoposChatApplication
import com.copopapel.coposchat.models.CoposchatContact
import com.copopapel.coposchat.models.CoposchatMessage
import kotlinx.android.synthetic.main.activity_contacts.*

class ContactsActivity : AppCompatActivity() {
    private var contactList = generateContactList()
    private var contactsAdapter = ContactsAdapter(contactList)
    private val TAG = "ContactsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        contacts_panel.layoutManager = LinearLayoutManager(this)
        contacts_panel.adapter = contactsAdapter
    }

    override fun onResume() {
        super.onResume()
        contactList = generateContactList()
        contactsAdapter = ContactsAdapter(contactList)
        contacts_panel.adapter = contactsAdapter
    }

    fun generateContactList(): ArrayList<CoposchatContact>{
        return (CoposChatApplication.instance.helperContatosDB?.fetchAllContatos() as ArrayList<CoposchatContact>)
    }

    fun contactClicked(view:View){
        Log.d(TAG,view.id.toString())
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("contact", contactList[view.tag as Int])

        startActivity(intent)
    }

    fun onClickAddContact(view:View){
        val intent = Intent(this, AddContactActivity::class.java)
        startActivity(intent)
    }
}