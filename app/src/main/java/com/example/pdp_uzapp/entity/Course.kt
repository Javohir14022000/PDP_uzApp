package com.example.pdp_uzapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val desc: String

) : Serializable