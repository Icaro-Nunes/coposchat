package com.copopapel.coposchat

import android.content.Context
import androidx.constraintlayout.solver.widgets.Helper
import androidx.test.core.app.ApplicationProvider
import com.copopapel.coposchat.helpers.HelperContatosDB
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ContactsActivityHelperTest {
    val context = ApplicationProvider.getApplicationContext<Context>()
    var helperDB:HelperContatosDB? = null

    @Test
    fun `Teste 01 - Teste se o fetchAll do DB com 2 contatos salvos retorna uma lista com 2 contatos`() {
        preparaMockTeste01()

        val listaResul = helperDB?.fetchAllContatos()

        assertEquals(2,listaResul?.size)
        helperDB?.close()
    }

    fun preparaMockTeste01(){
        helperDB = HelperContatosDB(context)
        helperDB?.addContato("Weber")
        helperDB?.addContato("Joao")
    }
}