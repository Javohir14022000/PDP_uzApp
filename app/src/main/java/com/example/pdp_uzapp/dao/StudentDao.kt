package uz.pdp.pdpapp.dao

import androidx.room.*
import com.example.pdp_uzapp.entity.Student

@Dao
interface StudentDao {

    @Insert
    fun addStudent(student: Student)

    @Update
    fun editStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Query("select * from student where group_id = :groupId")
    fun getStudentsByGroupId(groupId: Int): List<Student>

    @Query("select * from student where id = :id")
    fun getStudentById(id: Int): List<Student>
}