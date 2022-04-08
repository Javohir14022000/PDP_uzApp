package com.example.pdp_uzapp.fragments.course_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.FragmentCourseInfoBinding
import com.example.pdp_uzapp.entity.Course
import com.example.pdp_uzapp.entity.Student
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class CourseInfoFragment : Fragment(R.layout.fragment_course_info) {

    private val binding: FragmentCourseInfoBinding by viewBinding(FragmentCourseInfoBinding::bind)
    lateinit var course: Course
    lateinit var appDatabase: AppDatabase
    lateinit var student: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course = it.getSerializable("course") as Course
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appDatabase = AppDatabase.getInstance(requireContext())
        binding.apply {
            name.text = course.name
            textInfo.text = course.desc
            exitIcon.setOnClickListener {
                findNavController().popBackStack()
            }
            addStudent.setOnClickListener {

                if (appDatabase.groupDao().getGroups(courseId = course.id, isOpen = true).isNotEmpty()) {
                    val bundle = Bundle()
                    bundle.putSerializable("course_id", course.id)
                    bundle.putBoolean("boolean", true)
                    findNavController().navigate(R.id.addStudentFragment, bundle)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "${course.name}ga gruppa qo`shilmagan",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }


        }


    }
}