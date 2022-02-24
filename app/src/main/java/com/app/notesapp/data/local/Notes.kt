package com.app.notesapp.data.local

data class Notes(
    val id: String,
    val title: String,
    val desc: String,
    val date: String,
    val priority: Priority
)