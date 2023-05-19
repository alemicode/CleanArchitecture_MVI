package com.example.myapplication.bussines.data.cache.implementation

import com.example.myapplication.bussines.domain.model.cache.NoteCacheDataSource
import com.example.myapplication.bussines.domain.model.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteCacheDataSourceImpl
@Inject
constructor(
): NoteCacheDataSource {

    override suspend fun insertNote(note: Note): Long {
        return insertNote(note)
    }

    override suspend fun deleteNote(primaryKey: String): Int {
        return deleteNote(primaryKey)
    }

    override suspend fun deleteNotes(notes: List<Note>): Int {
        return deleteNotes(notes)
    }

    override suspend fun updateNote(
        primaryKey: String,
        newTitle: String,
        newBody: String?
    ): Int {
        return updateNote(primaryKey, newTitle, newBody)
    }

    override suspend fun searchNotes(
        query: String,
        filterAndOrder: String,
        page: Int
    ): List<Note> {
        TODO("Check filterAndOrder and make query")
    }

    override suspend fun searchNoteById(id: String): Note? {
        return searchNoteById(id)
    }

    override suspend fun getNumNotes(): Int {
        return getNumNotes()
    }

    override suspend fun insertNotes(notes: List<Note>): LongArray{
        return insertNotes(notes)
    }
}