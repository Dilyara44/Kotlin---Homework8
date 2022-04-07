package ru.netology

class WallService {
    private val notes = mutableMapOf<Long, Note>()
    private val comments = mutableMapOf<Long, Comment>()
    private var noteId = 1.toLong()
    private var commentId = 1.toLong()

    fun addNote(note: Note): Long {
        val noteWithId = note.copy(id = noteId)
        val target = noteId to noteWithId
        noteId++
        notes += target
        return noteWithId.id
    }

    fun createComment(comment: Comment): Long {
        if (notes.containsKey(comment.idNote)) {
            val commentWithId = comment.copy(id = commentId)
            val target = commentId to commentWithId
            commentId++
            comments += target
            return commentWithId.id
        }
        throw NoteNotFoundException("Такой заметки не существует!")
    }

    fun editNote(initialNote: Note): Boolean {
        if (notes.containsKey(initialNote.id) && !(notes.getValue(initialNote.id)).isDeleted) {
            notes[initialNote.id] = (notes.getValue(initialNote.id)).copy(
                ownerId = initialNote.ownerId,
                title = initialNote.title,
                text = initialNote.text,
                date = initialNote.date,
                privacyView = initialNote.privacyView,
                canComment = initialNote.canComment,
                textWiki = initialNote.textWiki,
            )
            return true
        } else if (notes.containsKey(initialNote.id) && (notes.getValue(initialNote.id)).isDeleted) {
            throw NoteDeletedException("Заметки не существует! Она была удалена!")
        }

        throw NoteNotFoundException("Такой заметки не существует!")
    }

    fun editComment(initialComment: Comment): Boolean {
        if (comments.containsKey(initialComment.id) && !(comments.getValue(initialComment.id)).isDeleted) {
            comments[initialComment.id] = (comments.getValue(initialComment.id)).copy(
                fromId = initialComment.fromId,
                date = initialComment.date,
                text = initialComment.text,
                donut = initialComment.donut,
                replyToUser = initialComment.replyToUser,
                replyToComment = initialComment.replyToComment,
                attachments = initialComment.attachments,
                parentsStack = initialComment.parentsStack,
                thread = initialComment.thread
            )
            return true
        } else if (comments.containsKey(initialComment.id) && (comments.getValue(initialComment.id)).isDeleted) {
            throw CommentDeletedException("Комментария не существует! Он был удален!")
        }
        throw CommentNotFoundException("Такого комментария не существует!")
    }

    fun deleteNote(id: Long): Boolean {
        if (notes.containsKey(id) && !(notes.getValue(id)).isDeleted) {
            notes[id] = (notes.getValue(id)).copy(
                isDeleted = true
            )
            for (comment in comments.values) {
                if (comment.idNote == id && !comment.isDeleted) {
                    comments[id] = comment.copy(
                        isDeleted = true
                    )
                }
            }
            return true
        } else if (notes.containsKey(id) && (notes.getValue(id)).isDeleted) {
            throw NoteDeletedException("Заметка была удалена ранее!")
        }
        throw NoteNotFoundException("Такой заметки не существует!")
    }

    fun deleteComment(id: Long): Boolean {
        if (comments.containsKey(id) && !(comments.getValue(id)).isDeleted) {
            comments[id] = (comments.getValue(id)).copy(
                isDeleted = true
            )
            return true
        } else if (comments.containsKey(id) && (comments.getValue(id)).isDeleted) {
            throw CommentDeletedException("Комментария не существует! Он был удален ранее!")
        }

        throw CommentNotFoundException("Такого комментария не существует!")
    }

    fun getNoteById(id: Long): Note {
        if (notes.containsKey(id) && !(notes.getValue(id)).isDeleted) {
            return notes.getValue(id)
        }
        throw NoteNotFoundException("Такой заметки не существует!")
    }

    fun getComments(id: Long): List<Comment> {
        val listOfComments = mutableListOf<Comment>()
        for (comment in comments.values) {
            if (comment.idNote == id && !comment.isDeleted) {
                listOfComments += comment
            }
        }
        if (listOfComments.isNotEmpty()) {
            return listOfComments
        } else {
            throw CommentNotFoundException("Для данной заметки нет комментариев")
        }
    }


    fun getNotes(id: Long): List <Note> {
        val listOfNotes = mutableListOf<Note>()
        for (note in notes.values) {
            if (note.ownerId == id) {
                listOfNotes += note
            }
        }
        if (listOfNotes.isNotEmpty()) {
            return listOfNotes
        } else {
            throw NoteNotFoundException("У пользователя еще нет заметок!")
        }
    }

    fun restoreComment(id: Long): Boolean {
        if (comments.containsKey(id) && (comments.getValue(id)).isDeleted) {
            comments[id] = (comments.getValue(id)).copy(
                isDeleted = false
            )
            return true
        } else if (comments.containsKey(id) && !(comments.getValue(id)).isDeleted) {
            throw CommentNotDeletedException("Комментарий не удален")
        }
        throw CommentNotFoundException("Комментарий не найден")
    }
}




