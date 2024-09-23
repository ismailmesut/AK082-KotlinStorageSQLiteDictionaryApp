package com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context,"dictionary.sqlite",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS 'words' (\n" +
                "\t'word_id'\tINTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "\t'english'\tTEXT,\n" +
                "\t'turkish'\tTEXT\n" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS words")
        onCreate(db)
    }
}
