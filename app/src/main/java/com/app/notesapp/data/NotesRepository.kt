package com.app.notesapp.data

import androidx.lifecycle.LiveData
import com.app.notesapp.data.local.entity.Notes
import com.app.notesapp.data.local.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    val getAllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insertNotes(notes: Notes){
        notesDao.addNote(notes)
    }
}