package com.example.pdp_uzapp.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pdp_uzapp.entity.Group
import com.example.pdp_uzapp.entity.Student

data class GroupWithStudent(
    @Embedded
    val group: Group,
    @Relation(
        parentColumn = "id",
        entityColumn = "group_id"
    )
    val list: List<Student>
)
