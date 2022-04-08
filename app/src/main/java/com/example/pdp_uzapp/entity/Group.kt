package com.example.pdp_uzapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.PrimaryKey

@Entity(
    tableName = "groups",
    foreignKeys = [
        ForeignKey(
            entity = Course::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("course_id"),
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Mentor::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("mentor_id"),
            onDelete = NO_ACTION
        )
    ]
)
data class Group(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "is_open")
    val isOpen: Boolean = false,
    @ColumnInfo(name = "course_id")
    val courseId: Int = 0,
    @ColumnInfo(name = "mentor_id")
    val mentorId: Int,

    val date: String,
    val time: String,
)
