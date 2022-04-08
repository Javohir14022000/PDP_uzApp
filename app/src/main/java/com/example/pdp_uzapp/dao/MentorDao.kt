package uz.pdp.pdpapp.dao

import androidx.room.*
import com.example.pdp_uzapp.entity.Mentor

@Dao
interface MentorDao {

    @Insert
    fun addMentor(mentor: Mentor)

    @Update
    fun editMentor(mentor: Mentor)

    @Delete
    fun deleteMentor(mentor: Mentor)

    @Query("select * from mentor")
    fun getListMentors(): List<Mentor>

    @Query("select * from mentor where courseId = :courseId")
    fun getAllMentors(courseId: Int): List<Mentor>
}