package com.copopapel.coposchat.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.copopapel.coposchat.adapters.MessageAdapter
import com.copopapel.coposchat.models.CoposchatContact
import com.copopapel.coposchat.models.CoposchatMessage
import java.util.ArrayList

class MainActivityViewModel: ViewModel() {
    var contact = MutableLiveData<CoposchatContact?>().apply { value = null }
    private set

    var messageList: MutableList<CoposchatMessage> = mutableListOf()

    lateinit var adapter: MessageAdapter
    private set

    var lastUpdatedMessage = MutableLiveData<Int>().apply { value = 0 }
    private set

    fun updateDataSet(newList: MutableList<CoposchatMessage>){
        messageList = newList
        adapter = MessageAdapter(messageList)
        adapter.notifyDataSetChanged()
        lastUpdatedMessage.value = messageList.lastIndex
    }

    fun addMessage(value: CoposchatMessage, pos: Int? = null){
        if(pos != null){
            messageList.add(pos ,value)
            adapter.notifyItemInserted(pos)
        } else {
            messageList.add(value)
            adapter.notifyItemInserted(messageList.lastIndex)
        }

        lastUpdatedMessage.value = messageList.lastIndex
    }

    fun createMessageList(num: Int): ArrayList<CoposchatMessage> {
        val theList = ArrayList<CoposchatMessage>()

        for(i in 1..num){
            theList.add(CoposchatMessage(i.toString(), "me"))
        }

        return theList
    }

    fun initDataset(){
        updateDataSet(createMessageList(7))
    }
}