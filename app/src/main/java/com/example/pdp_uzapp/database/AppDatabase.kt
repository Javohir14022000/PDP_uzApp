package com.example.pdp_uzapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pdp_uzapp.dao.GroupDao
import uz.pdp.pdpapp.dao.CourseDao
import uz.pdp.pdpapp.dao.MentorDao
import uz.pdp.pdpapp.dao.StudentDao
import com.example.pdp_uzapp.entity.Course
import com.example.pdp_uzapp.entity.Group
import com.example.pdp_uzapp.entity.Mentor
import com.example.pdp_uzapp.entity.Student

@Database(entities = [Student::class, Course::class, Group::class,  Mentor::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun groupDao(): GroupDao
    abstract fun mentorDao(): MentorDao

    companion object {

        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return appDatabase!!
        }
    }
}