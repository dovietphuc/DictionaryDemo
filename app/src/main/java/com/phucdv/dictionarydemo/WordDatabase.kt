package com.phucdv.dictionarydemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Word::class], version = 1
)
abstract class WordDatabase : RoomDatabase() {
    abstract val wordDao: WordDao

    companion object {
        private var _INSTANCE: WordDatabase? = null

        fun getInstance(context: Context) : WordDatabase {
            if(_INSTANCE == null) {
                _INSTANCE = Room.databaseBuilder(
                    context,
                    WordDatabase::class.java,
                    "word.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return _INSTANCE!!
        }
    }

}