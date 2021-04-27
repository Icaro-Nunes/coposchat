package com.copopapel.coposchat.application

import android.app.Application
import com.copopapel.coposchat.helpers.HelperContatosDB

class CoposChatApplication: Application() {
    var helperContatosDB: HelperContatosDB? = null
    private set

    companion object {
        lateinit var instance: CoposChatApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        helperContatosDB = HelperContatosDB(this)
    }
}