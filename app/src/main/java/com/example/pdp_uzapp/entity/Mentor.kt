package com.example.pdp_uzapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Course::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("courseId"),
        onDelete = CASCADE
    )]
)
data class  Mentor(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "first_name")
    val firstname: String,
    @ColumnInfo(name = "last_name")
    val lastname: String,
    @ColumnInfo(name = "patron")
    val patron: String,
    val courseId: Int
)
