package com.copopapel.coposchat.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.copopapel.coposchat.ActivityContacts
import com.copopapel.coposchat.R
import com.copopapel.coposchat.models.CoposchatContact
import com.copopapel.coposchat.models.CoposchatMessage

class ContactsAdapter(private val contacts: List<CoposchatContact>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val itemRoot = itemView.findViewById<ConstraintLayout>(R.id.contact_item_root)
        val contactName = itemView.findViewById<TextView>(R.id.contact_name)
        val lastMessage = itemView.findViewById<TextView>(R.id.last_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.contact_item_layout, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentContact = contacts.get(position)
        val theLastMessage = currentContact.lastMessage

        holder.itemRoot.tag = position

        holder.contactName.text = currentContact.name
        holder.lastMessage.text = "${theLastMessage.source}: ${theLastMessage.content}"
    }
}