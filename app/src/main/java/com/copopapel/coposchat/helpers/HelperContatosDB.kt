package com.copopapel.coposchat.helpers

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.copopapel.coposchat.models.CoposchatContact
import com.copopapel.coposchat.models.CoposchatMessage

class HelperContatosDB(context: Context):
    SQLiteOpenHelper(context, DB_NAME, null,DB_VERSION) {

    companion object {
        val DB_NAME = "contacts.db"
        val DB_VERSION = 1
    }

    private val TABLE_NAME = "contatos"

    private val COLUMN_ID = "id"
    private val COLUMN_NAME = "name"
    private val COLUMN_SRC_LAST_MESSAGE = "src_last_message"
    private val COLUMN_LAST_MESSAGE = "last_message_text"

    private val CREATE_COLUMN_ID = "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
    private val CREATE_COLUMN_NAME = "name TEXT NOT NULL"
    private val CREATE_COLUMN_SRC_LAST_MESSAGE = "src_last_message TEXT"
    private val CREATE_COLUMN_LAST_MESSAGE = "last_message_text TEXT"

    private val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($CREATE_COLUMN_ID, $CREATE_COLUMN_NAME, $CREATE_COLUMN_SRC_LAST_MESSAGE, $CREATE_COLUMN_LAST_MESSAGE);"

    private val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL(DROP_TABLE)
            onCreate(db)
        }
    }

    fun fetchAllContatos(): List<CoposchatContact>{
        val query = "SELECT * FROM $TABLE_NAME"
        val list = mutableListOf<CoposchatContact>()
        val db = readableDatabase ?: return mutableListOf()

        val cursor = db.rawQuery(query,null) ?: return mutableListOf()

        while(cursor.moveToNext()){
            list.add(
                CoposchatContact(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    CoposchatMessage(
                        cursor.getString(cursor.getColumnIndex(COLUMN_LAST_MESSAGE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SRC_LAST_MESSAGE))
                    )
                )
            )
        }
        db.close()
        return list
    }

    fun findContatoByName(name: String) :CoposchatContact?{
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME = ?"
        val arguments = arrayOf(name)

        var contato: CoposchatContact? = null
        val db = readableDatabase ?: return null

        val cursor = db.rawQuery(query,arguments) ?: return null

        while(cursor.moveToNext()){
            contato = CoposchatContact(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                CoposchatMessage(
                    cursor.getString(cursor.getColumnIndex(COLUMN_LAST_MESSAGE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_SRC_LAST_MESSAGE))
                )
            )
        }

        return contato
    }

    fun addContato(contactName: String): Int?{
        val instruction = "INSERT INTO $TABLE_NAME ($COLUMN_NAME) VALUES (?)"
        val arguments = arrayOf(contactName)

        val db = writableDatabase ?: return null
        db.execSQL(instruction,arguments)

        db.close()
        return findContatoByName(contactName)?.id
    }
}