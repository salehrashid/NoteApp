package com.app.notesapp.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import com.app.notesapp.data.local.Notes

@Dao
interface NotesDao {
    @Insert
    fun addNote(notes: Notes){

    }
}