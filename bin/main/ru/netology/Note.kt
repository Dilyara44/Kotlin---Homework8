package ru.netology

data class Note(
    val id: Long,
    val ownerId: Long,
    val title: String?,
    val text: String,
    val date: Int,
    val comments: Long?,
    val readComments: Long?,
    val viewUrl: String,
    val privacyView: String,
    val canComment: Boolean,
    val textWiki: String?,
    var isDeleted: Boolean
    )
