package com.vikram.domain.util

import com.vikram.domain.roomdatabase.models.Note

object DataUtils {
    object NoteData {
        val notes = mutableListOf<Note>().apply {
            for (i in 1..5) {
                add(
                    Note(
                        title = "test note $i",
                        content = "test",
                        date = System.currentTimeMillis()
                    )

                )
            }
        }
    }
}