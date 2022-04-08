package com.example.pdp_uzapp.dao

import androidx.room.*
import com.example.pdp_uzapp.entity.Group
import com.example.pdp_uzapp.models.GroupWithStudent


@Dao
interface GroupDao {

    @Insert
    fun addGroup(group: Group)

//    @Query("select * from groups where is_open = 'true'")
//    fun getOpenedGroups(): List<Group>
//
//    @Query("select * from groups where is_open = 'false'")
//    fun getUnOpenedGroups(): List<Group>

    @Query("select * from groups where course_id =:courseId and is_open = :isOpen")
    fun getGroups(courseId: Int = 0, isOpen: Boolean = false): List<Group>

    @Update
    fun editGroup(group: Group)

    @Delete
    fun deleteGroup(group: Group)

    @Query("select * from groups where id = :id")
    fun getGroupById(id: Int): Group

    @Transaction
    @Query("select * from groups")
    fun getGroup(): List<GroupWithStudent>
}