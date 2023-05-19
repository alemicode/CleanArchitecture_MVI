package com.example.myapplication.bussines.data.network.implementation

import com.example.myapplication.bussines.domain.model.network.NoteNetworkDataSource
import com.example.myapplication.bussines.domain.model.Note
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NoteNetworkDataSourceImpl
@Inject
constructor(
): NoteNetworkDataSource {

    override suspend fun insertOrUpdateNote(note: Note) {
        return insertOrUpdateNote(note)
    }

    override suspend fun deleteNote(primaryKey: String) {
        return deleteNote(primaryKey)
    }

    override suspend fun insertDeletedNote(note: Note) {
        return insertDeletedNote(note)
    }

    override suspend fun insertDeletedNotes(notes: List<Note>) {
        return insertDeletedNotes(notes)
    }

    override suspend fun deleteDeletedNote(note: Note) {
        return deleteDeletedNote(note)
    }

    override suspend fun getDeletedNotes(): List<Note> {
        return getDeletedNotes()
    }

    override suspend fun deleteAllNotes() {
        deleteAllNotes()
    }

    override suspend fun searchNote(note: Note): Note? {
        return searchNote(note)
    }

    override suspend fun getAllNotes(): List<Note> {
        return getAllNotes()
    }

    override suspend fun insertOrUpdateNotes(notes: List<Note>) {
        return insertOrUpdateNotes(notes)
    }


}