package com.example.fragments

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.Contacts

class DatabaseHelper(context: Context):SQLiteOpenHelper(context, DATABASER_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASER_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "contacts"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME"+
                "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "$COLUMN_NAME TEXT, "+
                "$COLUMN_PHONE TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addContact(name: String, phone: String){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_PHONE, phone)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllContacts(): List<Contact>{
        val contactList = mutableListOf<Contact>()
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"

        return contactList
    }
}

data class Contact(val id: Int, val name: String, val phone: String)