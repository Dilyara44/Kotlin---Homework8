package ru.netology

data class Comment(
    val id: Long,
    val fromId: Long,
    val idNote: Long,
    val date: Int,
    val text: String,
    val donut: Donut,
    val replyToUser: Long?,
    val replyToComment: Long?,
    val attachments: String?,
    val parentsStack: String?,
    val thread: String,
    var isDeleted: Boolean

)
