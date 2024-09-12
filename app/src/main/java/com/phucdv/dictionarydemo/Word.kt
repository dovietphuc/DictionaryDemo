package com.phucdv.dictionarydemo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String
)