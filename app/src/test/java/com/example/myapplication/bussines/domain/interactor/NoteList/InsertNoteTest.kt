package com.example.myapplication.bussines.domain.interactor.NoteList

import com.codingwithmitch.cleannotes.business.domain.model.NoteFactory
import com.example.myapplication.bussines.data.cache.FORCE_GENERAL_FAILURE
import com.example.myapplication.bussines.domain.interactor.InsertNewNote
import com.example.myapplication.bussines.domain.model.cache.NoteCacheDataSource
import com.example.myapplication.bussines.domain.model.network.NoteNetworkDataSource
import com.example.myapplication.di.DependencyContainer
import com.example.myapplication.framework.presentation.notelist.state.NoteListStateEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions
import java.util.*

class InsertNoteTest {
    private lateinit var insertnewNote: InsertNewNote
    private var dependencyContainer: DependencyContainer = DependencyContainer()
    private var noteNetworkDataSource: NoteNetworkDataSource
    private var noteCacheDataSource: NoteCacheDataSource
    private var noteFactory: NoteFactory

    init {
        dependencyContainer.build()
        noteFactory = dependencyContainer.noteFactory
        noteNetworkDataSource = dependencyContainer.noteNetworkDataSource
        noteCacheDataSource = dependencyContainer.noteCacheDataSource
    }

    @Test
    fun insertNote_success_confirmNetworkAndCacheUpdate() = runBlocking {

        val newNote = noteFactory.createSingleNote(
            id = UUID.randomUUID().toString(),
            title = UUID.randomUUID().toString(),
            body = UUID.randomUUID().toString()
        )
        insertnewNote = InsertNewNote(
            noteCacheDataSource = noteCacheDataSource,
            noteNetworkDataSource = noteNetworkDataSource,
            noteFactory = noteFactory
        )
        insertnewNote.insetNote(
            newNote.id,
            newNote.title,
            newNote.body,
            stateEvent = NoteListStateEvent.InsertNewNoteEvent(newNote.title, newNote.body)
        ).collect {
            Assertions.assertEquals(
                it?.stateMessage?.response?.message,
                InsertNewNote.INSERT_NOTE_SUCCESS
            )
        }
        val insertedNote = noteNetworkDataSource.searchNote(newNote)
        Assertions.assertEquals(
            insertedNote,
            newNote
        )

    }

    @Test
    fun insertNote_failed_confirmNetworkAndCacheUpdate() = runBlocking {
        val newNote = noteFactory.createSingleNote(
            id = FORCE_GENERAL_FAILURE,
            title = UUID.randomUUID().toString(),
            body = UUID.randomUUID().toString()
        )
        insertnewNote = InsertNewNote(
            noteCacheDataSource = noteCacheDataSource,
            noteNetworkDataSource = noteNetworkDataSource,
            noteFactory = noteFactory
        )
        insertnewNote.insetNote(
            id = newNote.id,
            title = newNote.title,
            body = newNote.body,
            stateEvent = NoteListStateEvent.InsertNewNoteEvent(newNote.title, newNote.body)
        ).collect {
            Assertions.assertEquals(
                it?.stateMessage?.response?.message,
                InsertNewNote.INSERT_NOTE_FAILED
            )
        }
        val insertedNote = noteNetworkDataSource.searchNote(newNote)
        Assertions.assertTrue(
            insertedNote == null
        )
    }
}