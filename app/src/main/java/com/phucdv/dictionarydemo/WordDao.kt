package com.phucdv.dictionarydemo

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    fun getAllWords(): List<Word>

    @Query("SELECT * FROM word")
    fun getAllWordsCursor(): Cursor

    @Query("SELECT * FROM word WHERE id=:id")
    fun getWord(id: Int) : Word?

    @Query("SELECT * FROM word WHERE id=:id")
    fun getWordCursor(id: Int) : Cursor

    @Insert
    fun insert(word: Word)
}