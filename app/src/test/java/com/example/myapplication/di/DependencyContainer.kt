package com.example.myapplication.di

import com.codingwithmitch.cleannotes.business.domain.model.NoteFactory
import com.codingwithmitch.cleannotes.business.domain.util.DateUtil
import com.example.myapplication.bussines.data.cache.FakeNoteCacheDataSourceImpl
import com.example.myapplication.bussines.data.cache.implementation.NoteCacheDataSourceImpl
import com.example.myapplication.bussines.data.network.FakeNoteNetworkDataSourceImpl
import com.example.myapplication.bussines.domain.model.Note
import com.example.myapplication.bussines.domain.model.cache.NoteCacheDataSource
import com.example.myapplication.bussines.domain.model.network.NoteNetworkDataSource
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class DependencyContainer {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH)
    val dateUtil = DateUtil(dateFormat)
    lateinit var noteFactory: NoteFactory
    lateinit var noteCacheDataSource: NoteCacheDataSource
    lateinit var noteNetworkDataSource: NoteNetworkDataSource

    fun build() {
        noteFactory = NoteFactory(dateUtil)

        noteCacheDataSource = FakeNoteCacheDataSourceImpl(
            notesData = HashMap<String, Note>(),
            dateUtil
        )

        noteNetworkDataSource = FakeNoteNetworkDataSourceImpl(
            notesData = HashMap<String, Note>(),
            deletedNotesData = HashMap<String, Note>()

        )

    }
}