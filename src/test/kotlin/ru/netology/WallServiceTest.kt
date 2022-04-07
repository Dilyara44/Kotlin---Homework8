package ru.netology

import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun note_should_be_added() {
        val service = WallService()

        val actual = service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        val result = 1.toLong()

        assertEquals(result, actual)
    }

    @Test
    fun comment_should_be_added() {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        val actual = service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false ))

        val result = 1.toLong ()

        assertEquals(result, actual)
    }

    @Test(expected = NoteNotFoundException::class)
    fun comment_should_not_be_added() {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        val actual = service.createComment (
            Comment (
                0,
                1,
                6,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false ))

        val result = 1.toLong ()

        assertEquals(result, actual)
    }

    @Test
    fun note_should_be_edited() {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        val result = service.editNote(Note(
            1,
            1,
            "test_title",
            "test_text",
            1,
            1,
            1,
            "URL",
            "privacy",
            true,
            null,
            false
        ))

        assertTrue(result)

    }

    @Test(expected = NoteDeletedException::class)
    fun note_should_not_be_edited_Deleted_note () {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                true
            )
        )

        service.editNote(Note(
            1,
            1,
            "test_title",
            "test_text",
            1,
            1,
            1,
            "URL",
            "privacy",
            true,
            null,
            true
        ))
    }

    @Test(expected = NoteNotFoundException::class)
    fun note_should_not_be_edited_Note_not_found () {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.editNote(Note(
            13,
            1,
            "test_title",
            "test_text",
            1,
            1,
            1,
            "URL",
            "privacy",
            true,
            null,
            false
        ))
    }

    @Test
    fun comment_should_be_edited() {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false ))

        val actual = service.editComment (
            Comment (
                1,
                1,
                1,
                1,
                "test! Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false )
                )

        assertTrue (actual)

    }

    @Test (expected = CommentDeletedException::class)
    fun comment_should_not_be_edited_Deleted_comment() {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                true ))

        service.editComment (
            Comment (
                1,
                1,
                1,
                1,
                "test! Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false )
        )



    }

    @Test (expected = CommentNotFoundException::class)
    fun comment_should_not_be_edited_Comment_not_found() {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                true ))

        service.editComment (
            Comment (
                34,
                1,
                1,
                1,
                "test! Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false )
        )



    }

    @Test
    fun note_should_be_deleted() {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false ))

        val actual = service.deleteNote(1)

        assertTrue(actual)
    }

    @Test (expected = NoteDeletedException::class)
    fun note_should_not_be_deleted_Deleted_note () {

        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                true
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false ))

        service.deleteNote(1)

    }

    @Test (expected = NoteNotFoundException::class)
    fun note_should_not_be_deleted_Note_not_found () {

        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false ))

        service.deleteNote(232)

    }

    @Test
    fun comment_should_be_deleted() {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false ))

        val actual = service.deleteComment(1)

        assertTrue(actual)
    }

    @Test (expected = CommentDeletedException::class)
    fun comment_should_not_be_deleted_Deleted_comment () {

        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                true
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                true ))

        service.deleteComment(1)

    }

    @Test (expected = CommentNotFoundException::class)
    fun comment_should_not_be_deleted_Comment_not_found () {

        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false ))

        service.deleteComment(123)

    }

    @Test
    fun get_note_by_id() {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        val actual = service.getNoteById(1)

        val result = Note(
            1,
            1,
            "title",
            "text",
            1,
            1,
            1,
            "URL",
            "privacy",
            true,
            null,
            false
        )

        assertEquals(result, actual)
    }

    @Test (expected = NoteNotFoundException::class)
    fun get_note_by_id_Note_not_found () {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.getNoteById(112)
    }

    @Test
    fun get_comments() {
        val service = WallService()
        val listOfComments = mutableListOf<Comment>()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false ))

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text1",
                Donut (true, "test1"),
                1,
                1,
                null,
                null,
                "test1",
                false ))

        listOfComments += Comment (
            1,
            1,
            1,
            1,
            "Comment text",
            Donut (true, "test"),
            1,
            1,
            null,
            null,
            "test",
            false )

        listOfComments += Comment (
            2,
            1,
            1,
            1,
            "Comment text1",
            Donut (true, "test1"),
            1,
            1,
            null,
            null,
            "test1",
            false )



        val actual = service.getComments(1)

        assertEquals (listOfComments, actual)
    }

    @Test (expected = CommentNotFoundException::class)
    fun get_comments_Comments_not_found () {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.getComments (1)
    }

    @Test
    fun get_notes() {
        val service = WallService()
        val listOfNotes = mutableListOf<Note>()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        listOfNotes +=Note (
            1,
            1,
            "title",
            "text",
            1,
            1,
            1,
            "URL",
            "privacy",
            true,
            null,
            false
        )

        listOfNotes +=Note (
            2,
            1,
            "title",
            "text",
            1,
            1,
            1,
            "URL",
            "privacy",
            true,
            null,
            false
        )

        val result = service.getNotes (1)

        assertEquals (listOfNotes, result )
    }

    @Test (expected = NoteNotFoundException::class)
    fun get_notes_Notes_not_found () {
        val service = WallService()

        service.getNotes(2)
    }

    @Test
    fun restore_comment() {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                true ))

        val result = service.restoreComment(1)

        assertTrue (result)

    }

    @Test (expected = CommentNotDeletedException::class)
    fun restore_comment_Comment_not_deleted () {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.createComment (
            Comment (
                0,
                1,
                1,
                1,
                "Comment text",
                Donut (true, "test"),
                1,
                1,
                null,
                null,
                "test",
                false ))

        service.restoreComment(1)

    }

    @Test (expected = CommentNotFoundException::class)
    fun restore_comment_Comment_not_found () {
        val service = WallService()

        service.addNote(
            Note(
                0,
                1,
                "title",
                "text",
                1,
                1,
                1,
                "URL",
                "privacy",
                true,
                null,
                false
            )
        )

        service.restoreComment(123)

    }
}