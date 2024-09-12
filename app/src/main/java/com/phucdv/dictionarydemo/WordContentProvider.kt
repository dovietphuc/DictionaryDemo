package com.phucdv.dictionarydemo

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.core.net.toUri

class WordContentProvider : ContentProvider() {
    companion object {
        val AUTHORITY = "com.phucdv.dictionarydemo.provider"
        val WORD_TABLE_NAME = "word"
        val URI = "content://$AUTHORITY/$WORD_TABLE_NAME".toUri()
    }

    private lateinit var database: WordDatabase

    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, WORD_TABLE_NAME, 1)
        addURI(AUTHORITY, "$WORD_TABLE_NAME/#", 2)
    }

    override fun onCreate(): Boolean {
        database = WordDatabase.getInstance(context!!)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when(sUriMatcher.match(uri)) {
            1 -> {
                database.wordDao.getAllWordsCursor()
            }
            2 -> {
                val id = uri.lastPathSegment?.toInt() ?: 0
                database.wordDao.getWordCursor(id)
            }
            else -> {
                null
            }
        }
    }

    override fun getType(uri: Uri): String? {
        return when(sUriMatcher.match(uri)) {
            1 -> {
                "vnd.android.cursor.dir/vnd.com.phucdv.dictionarydemo.$WORD_TABLE_NAME"
            }
            2 -> {
                "vnd.android.cursor.item/vnd.com.phucdv.dictionarydemo.$WORD_TABLE_NAME"
            }
            else -> {
                throw IllegalArgumentException("Unsupported URI: $uri")
            }
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}