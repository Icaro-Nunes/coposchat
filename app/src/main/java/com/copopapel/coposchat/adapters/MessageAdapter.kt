package com.copopapel.coposchat.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.copopapel.coposchat.R
import com.copopapel.coposchat.models.CoposchatMessage

class MessageAdapter(private val messages: List<CoposchatMessage>): RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val itemRoot = itemView.findViewById<LinearLayout>(R.id.item_root)
        val contentTextView = itemView.findViewById<TextView>(R.id.message_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val messageView = inflater.inflate(R.layout.message_item_layout, parent, false)
        // Return a new holder instance
        return ViewHolder(messageView)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mess = messages.get(position)

        val textView = holder.contentTextView
        textView.text = mess.content

        val container = holder.itemRoot

        if(mess.source == "me") {
            container.gravity = (Gravity.CENTER_VERTICAL) or (Gravity.END)
        } else {
            container.gravity = (Gravity.CENTER_VERTICAL) or (Gravity.START)
        }
    }
}