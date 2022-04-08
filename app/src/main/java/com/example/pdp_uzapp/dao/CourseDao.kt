package uz.pdp.pdpapp.dao

import androidx.room.*
import com.example.pdp_uzapp.entity.Course

@Dao
interface CourseDao {

    @Insert
    fun addCourse(course: Course)


    @Query("select * from `course`")
    fun getListCourse() :List<Course>
}