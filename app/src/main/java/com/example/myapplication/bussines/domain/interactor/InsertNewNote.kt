package com.example.myapplication.bussines.domain.interactor

import com.codingwithmitch.cleannotes.business.domain.model.NoteFactory
import com.example.myapplication.bussines.domain.model.cache.NoteCacheDataSource
import com.example.myapplication.bussines.domain.model.network.NoteNetworkDataSource
import com.example.myapplication.bussines.domain.model.Note
import com.example.myapplication.bussines.domain.state.*
import com.example.myapplication.framework.presentation.notelist.state.NoteListViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class InsertNewNote constructor(
    private val noteCacheDataSource: NoteCacheDataSource,
    private val noteNetworkDataSource: NoteNetworkDataSource,
    private val noteFactory: NoteFactory
) {
    suspend fun insetNote(
        id: String? = null,
        title: String,
        body: String,
        stateEvent: StateEvent
    ): Flow<DataState<NoteListViewState>?> = flow {
        val newNote = noteFactory.createSingleNote(
            id = id ?: UUID.randomUUID().toString(),
            title = title,
            body = body
        )
        val cacheResult = noteCacheDataSource.insertNote(newNote)
        var cacheResponse: DataState<NoteListViewState> = DataState()
        if (cacheResult > 0) {
            val viewState = NoteListViewState(
                newNote = newNote
            )
            cacheResponse = DataState.data(
                response = Response(
                    message = INSERT_NOTE_SUCCESS,
                    uiComponentType = UIComponentType.Toast(),
                    messageType = MessageType.Success()
                ),
                data = viewState,
                stateEvent = stateEvent
            )
        } else {
            cacheResponse = DataState.data(
                response = Response(
                    message = INSERT_NOTE_FAILED,
                    uiComponentType = UIComponentType.Toast(),
                    messageType = MessageType.Error()
                ),
                data = null,
                stateEvent = stateEvent
            )
        }

        emit(cacheResponse)


        updateNetwork(cacheResponse?.stateMessage?.response?.message, newNote)
    }

    private suspend fun updateNetwork(cacheResponse: String?, newNote: Note) {
        if (cacheResponse.equals(INSERT_NOTE_SUCCESS)) {
            noteNetworkDataSource.insertOrUpdateNote(newNote)
        }
    }

    companion object {
        val INSERT_NOTE_SUCCESS = "Successfully inserted new note."
        val INSERT_NOTE_FAILED = "Failed to insert new note."
    }
}
