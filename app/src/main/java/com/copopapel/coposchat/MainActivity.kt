package com.copopapel.coposchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.copopapel.coposchat.models.CoposchatMessage
import com.copopapel.coposchat.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        if(mViewModel != null) return

        mViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mViewModel?.contact?.value = intent.getParcelableExtra("contact")
        mViewModel?.initDataset()


        mViewModel?.lastUpdatedMessage?.observe(this, Observer { valor->
            messagePanel.scrollToPosition(valor)
        })

        if(mViewModel?.contact?.value != null){
            mViewModel?.addMessage(mViewModel?.contact?.value?.lastMessage!!)
            mViewModel?.contact?.observe(this,Observer{valor ->
                supportActionBar?.title = valor?.name
            })
        }
    }


    private fun setupToolbar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupRecyclerView() {
        val lManager = LinearLayoutManager(this)
        lManager.stackFromEnd = true
        messagePanel.layoutManager = lManager

        messagePanel.adapter = mViewModel?.adapter
    }

    fun addMessage(view:View){
        mViewModel?.addMessage(CoposchatMessage(editText1.text.toString(),"me"))
        editText1.setText("")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}