package com.task.noteapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.task.noteapp.data.model.TableConstants.DATE_FIELD
import com.task.noteapp.data.model.TableConstants.EDITED_TAG_FIELD
import com.task.noteapp.data.model.TableConstants.ID_FIELD
import com.task.noteapp.data.model.TableConstants.IMAGE_URL
import com.task.noteapp.data.model.TableConstants.NOTE_FIELD
import com.task.noteapp.data.model.TableConstants.NOTE_TITLE_FIELD
import com.task.noteapp.data.model.TableConstants.TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class NoteModel(
    @ColumnInfo(name = IMAGE_URL)
    val imageUrl: String?,
    @ColumnInfo(name = DATE_FIELD)
    val date: Long,
    @ColumnInfo(name = NOTE_TITLE_FIELD)
    val noteTitle: String,
    @ColumnInfo(name = NOTE_FIELD)
    val note: String,
    @ColumnInfo(name = EDITED_TAG_FIELD)
    val editedTag: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_FIELD)
    val id: Int? = null
) : Parcelable

object TableConstants{
    const val TABLE_NAME = "notes_table"
    const val IMAGE_URL = "image_url"
    const val DATE_FIELD = "date"
    const val NOTE_TITLE_FIELD = "note_title"
    const val NOTE_FIELD = "note"
    const val EDITED_TAG_FIELD = "edited_tag"
    const val ID_FIELD = "id"
}