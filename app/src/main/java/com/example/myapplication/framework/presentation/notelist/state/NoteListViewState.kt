package com.example.myapplication.framework.presentation.notelist.state

import android.os.Parcelable
import com.example.myapplication.bussines.domain.model.Note
import com.example.myapplication.bussines.domain.state.ViewState
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteListViewState(

    var noteList: ArrayList<Note>? = null,
    var newNote: Note? = null, // note that can be created with fab
    var notePendingDelete: NotePendingDelete? = null, // set when delete is pending (can be undone)
    var searchQuery: String? = null,
    var page: Int? = null,
    var isQueryExhausted: Boolean? = null,
    var filter: String? = null,
    var order: String? = null,
    var layoutManagerState: Parcelable? = null,
    var numNotesInCache: Int? = null

) : Parcelable, ViewState {

    @Parcelize
    data class NotePendingDelete(
        var note: Note? = null,
        var listPosition: Int? = null
    ) : Parcelable
}