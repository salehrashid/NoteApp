package com.app.notesapp.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_notes")
@Parcelize

data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val title: String,
    val desc: String,
    val date: String,
    val priority: Priority
): Parcelable