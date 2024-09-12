package com.phucdv.dictionarydemo

import android.content.ContentUris
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        buildFakeData()

        val projection = arrayOf(
            "id",
            "text"
        )
        val cursor = contentResolver.query(
            ContentUris.appendId(WordContentProvider.URI.buildUpon(), 10).build(),
            projection,
            null,
            null,
            null
        )
        cursor?.let { cursor ->
            while(cursor.moveToNext()) {
                val idIndex = cursor.getColumnIndex("id")
                val textIndex = cursor.getColumnIndex("text")

                val id = cursor.getInt(idIndex)
                val text = cursor.getString(textIndex)

                Log.d("PhucDV", "$id - $text")
            }
            cursor.close()
        }
    }

    private fun buildFakeData() {
        lifecycleScope.launch {
            val dataBase = WordDatabase.getInstance(application)

            withContext(Dispatchers.IO) {

                dataBase.wordDao.insert(Word(text = "Abcadsj"))
                dataBase.wordDao.insert(Word(text = "dfgh"))
                dataBase.wordDao.insert(Word(text = "dfbdb"))
                dataBase.wordDao.insert(Word(text = "34t34"))
                dataBase.wordDao.insert(Word(text = "wefaw"))
                dataBase.wordDao.insert(Word(text = "etsn"))
                dataBase.wordDao.insert(Word(text = "fnd"))
                dataBase.wordDao.insert(Word(text = "sdf"))
                dataBase.wordDao.insert(Word(text = "dsfv"))
                dataBase.wordDao.insert(Word(text = "Abcaethehdsj"))
                dataBase.wordDao.insert(Word(text = "dfgdf"))
                dataBase.wordDao.insert(Word(text = "gdfb"))
                dataBase.wordDao.insert(Word(text = "fgetesn"))
                dataBase.wordDao.insert(Word(text = "tr34t"))
                dataBase.wordDao.insert(Word(text = "dsfgdfg"))
            }

            withContext(Dispatchers.IO) {
                val allWord = dataBase.wordDao.getAllWords()
                allWord.forEach {
                    Log.d("PhucDV", "${it.text}")
                }
            }
        }
    }
}