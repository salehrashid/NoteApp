package com.app.notesapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app.notesapp.data.NotesRepository
import com.app.notesapp.data.local.entity.Notes
import com.app.notesapp.data.local.room.NotesDao
import com.app.notesapp.data.local.room.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {
    private val notesDao: NotesDao = NotesDatabase.getDatabase(application).notesDao()
    private val notesRepository: NotesRepository = NotesRepository(notesDao)

    val getAllNotes: LiveData<List<Notes>> = notesRepository.getAllNotes

    fun insertNotes(notes: Notes){
        viewModelScope.launch (Dispatchers.IO){
            notesRepository.insertNotes(notes)
        }
    }
}