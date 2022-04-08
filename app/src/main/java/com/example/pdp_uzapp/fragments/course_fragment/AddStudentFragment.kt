package com.example.pdp_uzapp.fragments.course_fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.adapters.SpinnerAdapter
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.FragmentAddStudentBinding
import com.example.pdp_uzapp.entity.Course
import com.example.pdp_uzapp.entity.Group
import com.example.pdp_uzapp.entity.Mentor
import com.example.pdp_uzapp.entity.Student
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class AddStudentFragment : Fragment(R.layout.fragment_add_student) {

    private val binding: FragmentAddStudentBinding by viewBinding(FragmentAddStudentBinding::bind)
    lateinit var course: Course
    lateinit var student: Student
    lateinit var appDatabase: AppDatabase
    lateinit var list_group: List<Group>
    lateinit var list: List<Mentor>
    private var boolean: Boolean? = false
    lateinit var spinnerAdapter: SpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course = it.getInt("course_id", 0) as Course
            boolean = it.getBoolean("boolean")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appDatabase = AppDatabase.getInstance(requireContext())
        binding.apply {
            if (boolean!!) {
                list = ArrayList(appDatabase.mentorDao().getAllMentors(courseId = -1))
                spinnerAdapter = SpinnerAdapter(list)
                spinGroupEdit.adapter = spinnerAdapter
            }

            layoutDate.setOnClickListener {
                val dialog =
                    DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                        override fun onDateSet(
                            view: DatePicker?,
                            year: Int,
                            month: Int,
                            dayOfMonth: Int
                        ) {
                            date.text = "$dayOfMonth/${month + 1}/$year"
                        }

                    }, 2021, 8, 19)

                dialog.show()
            }
            backBtn.setOnClickListener { findNavController().popBackStack() }

            if (!boolean!!) {
                spinCourse.visibility = View.GONE
                if (student.id != -1) {
                    lastname.setText(student.lastname)
                    name.setText(student.firstName)
                    fatherName.setText(student.lastname)
                    date.setText(student.registerDate)

                    save.setOnClickListener {
                        if (!boolean!! && student.id != -1) {
                            val surname = lastname.text.toString()
                            val name = name.text.toString()
                            val lastname = fatherName.text.toString()
                            val date = date.text.toString()

                            if (surname.isNotEmpty() && name.isNotEmpty() && lastname.isNotEmpty() && date.isNotEmpty()) {
                                val student1 = Student(
                                    id = student.id,
                                    firstName = name,
                                    lastname = surname,
                                    patron = lastname,
                                    registerDate = date,
                                    groupId = student.groupId
                                )
                                appDatabase.studentDao().editStudent(student1)
                                findNavController().popBackStack()
                            } else Toast.makeText(
                                requireContext(),
                                "Please fill all the fields",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (!boolean!!) {
                            val surname = lastname.text.toString()
                            val name = name.text.toString()
                            val lastname = fatherName.text.toString()
                            val date = date.text.toString()

                            if (surname.isNotEmpty() && name.isNotEmpty() && lastname.isNotEmpty() && date.isNotEmpty()) {
                                val student = Student(
                                    firstName = name,
                                    lastname = surname,
                                    patron = lastname,
                                    registerDate = date,
                                    groupId = -1
                                )
                                appDatabase.studentDao().addStudent(student)
                                findNavController().popBackStack()
                            } else Toast.makeText(
                                requireContext(),
                                "Please fill all the fields",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val surname = lastname.text.toString()
                            val name = name.text.toString()
                            val lastname = fatherName.text.toString()
                            val date = date.text.toString()
                            val group = list[spinGroupEdit.selectedItemPosition]

                            if (surname.isNotEmpty() && name.isNotEmpty() && lastname.isNotEmpty() && date.isNotEmpty() && group.firstname.isNotEmpty()) {
                                val student = Student(
                                    firstName = name,
                                    lastname = surname,
                                    patron = lastname,
                                    registerDate = date,
                                    groupId = group.id,
                                )
                                appDatabase.studentDao().addStudent(student)

                                findNavController().popBackStack(R.id.addStudentFragment, false)
                            } else Toast.makeText(
                                requireContext(),
                                "Please fill all the fields",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }
        }
    }

}