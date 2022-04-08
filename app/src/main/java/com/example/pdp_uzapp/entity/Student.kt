package com.example.pdp_uzapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
//    foreignKeys = [
//        ForeignKey(
//        entity = Group::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("group_id"),
//        onDelete = CASCADE
//    )]
)
data class Student(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name = "last_name")
    var lastname: String,
    @ColumnInfo(name = "patron")
    val patron: String,
    @ColumnInfo(name = "register_id")
    var registerDate: String,
    @ColumnInfo(name = "group_id")
    var groupId: Int,

//    var mentorId: Int
)
