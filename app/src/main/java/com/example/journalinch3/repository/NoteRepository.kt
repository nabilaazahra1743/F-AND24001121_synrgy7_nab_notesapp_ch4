package com.example.journalinch3.repository

import androidx.room.Query
import com.example.journalinch3.database.NoteDatabase
import com.example.journalinch3.model.Note

//repository-> place or conatiner to store data
class NoteRepository (private val db:NoteDatabase){

    //call notedao
    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNote(query: String) = db.getNoteDao().searchNote(query)
    //fun searchNote(query: String?) = db.getNoteDao().searchNote(query)
}