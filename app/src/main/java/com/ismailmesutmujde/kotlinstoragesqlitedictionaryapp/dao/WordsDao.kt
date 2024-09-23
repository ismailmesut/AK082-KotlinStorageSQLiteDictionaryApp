package com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.dao

import android.annotation.SuppressLint
import com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.database.DatabaseHelper
import com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.model.Words

class WordsDao {
    @SuppressLint("Range")
    fun allWords(dbh: DatabaseHelper) : ArrayList<Words> {
        val wordsList = ArrayList<Words>()
        val db = dbh.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM words", null)

        while (cursor.moveToNext()) {
            val word = Words(cursor.getInt(cursor.getColumnIndex("word_id")),
                cursor.getString(cursor.getColumnIndex("english")),
                cursor.getString(cursor.getColumnIndex("turkish")))
            wordsList.add(word)
        }
        return wordsList
    }

    @SuppressLint("Range")
    fun search(dbh:DatabaseHelper,searchWord:String) : ArrayList<Words> {
        val wordsList = ArrayList<Words>()
        val db = dbh.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM words WHERE english like '%$searchWord%'", null)

        while (cursor.moveToNext()) {
            val word = Words(cursor.getInt(cursor.getColumnIndex("word_id")),
                cursor.getString(cursor.getColumnIndex("english")),
                cursor.getString(cursor.getColumnIndex("turkish")))
            wordsList.add(word)
        }
        return wordsList
    }


}