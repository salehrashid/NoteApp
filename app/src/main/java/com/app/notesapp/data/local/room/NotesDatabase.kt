package com.app.notesapp.data.local.room

import android.content.Context
import androidx.room.*
import com.app.notesapp.data.local.entity.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object{
        @Volatile
        var instance: NotesDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NotesDatabase{
            if (instance == null){
                synchronized(this){
                    instance = Room.databaseBuilder(
                        context,
                        NotesDatabase::class.java,
                        "notes.db"
                    ).build()
                }
            }
            return instance as NotesDatabase
        }
    }
}