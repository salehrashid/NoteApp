package com.app.notesapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.notesapp.data.local.entity.Notes

@Dao
interface NotesDao {
    @Insert
    suspend fun addNote(notes: Notes)

    @Query("SELECT * FROM tb_notes ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Notes>>
}