package com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.DatabaseCopyHelper
import com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.R
import com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.adapter.WordsRecyclerViewAdapter
import com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.dao.WordsDao
import com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.database.DatabaseHelper
import com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.databinding.ActivityMainScreenBinding
import com.ismailmesutmujde.kotlinstoragesqlitedictionaryapp.model.Words


class MainScreenActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var bindingMainScreen : ActivityMainScreenBinding

    private lateinit var wordsList:ArrayList<Words>
    private lateinit var adapter:WordsRecyclerViewAdapter
    private lateinit var dbh: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainScreen = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = bindingMainScreen.root
        setContentView(view)

        copyDatabase()

        bindingMainScreen.toolbar.title = "Dictionary Application"
        setSupportActionBar(bindingMainScreen.toolbar)

        bindingMainScreen.recyclerView.setHasFixedSize(true)
        bindingMainScreen.recyclerView.layoutManager = LinearLayoutManager(this)

        dbh = DatabaseHelper(this)

        wordsList = WordsDao().allWords(dbh)


        /*
        wordsList = ArrayList()
        val w1 = Words(1, "Dog","Köpek")
        val w2 = Words(2, "Fish","Balık")
        val w3 = Words(3, "Table","Masa")

        wordsList.add(w1)
        wordsList.add(w2)
        wordsList.add(w3)

        */


        adapter = WordsRecyclerViewAdapter(this, wordsList)
        bindingMainScreen.recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        search(query)
        Log.e("Sent Search", query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        search(newText)
        Log.e("As letters are entered", newText)
        return true
    }

    fun copyDatabase() {
        val copyHelper = DatabaseCopyHelper(this)

        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }

    fun search(searchWord:String) {
        wordsList = WordsDao().search(dbh,searchWord)
        adapter = WordsRecyclerViewAdapter(this, wordsList)
        bindingMainScreen.recyclerView.adapter = adapter
    }


}