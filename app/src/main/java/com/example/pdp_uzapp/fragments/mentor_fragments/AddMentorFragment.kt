package com.example.pdp_uzapp.fragments.mentor_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.FragmentAddMentorBinding
import com.example.pdp_uzapp.entity.Mentor
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class AddMentorFragment : Fragment(R.layout.fragment_add_mentor) {

    private val binding: FragmentAddMentorBinding by viewBinding(FragmentAddMentorBinding::bind)
    lateinit var appDatabase: AppDatabase
    private var course: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course = it.getInt("course_add_mentor")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appDatabase = AppDatabase.getInstance(requireContext())

        binding.apply {


            save.setOnClickListener {

                val name = name.text.toString()
                val surname = surname.text.toString()
                val patron = patron.text.toString()
                val course = course

                if (surname.isNotEmpty() && name.isNotEmpty() && patron.isNotEmpty()) {
                    val mentor = Mentor(
                        firstname = name,
                        lastname = surname,
                        patron = patron,
                        courseId = course
                    )

                    appDatabase.mentorDao().addMentor(mentor)
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Maydonlarni to`liq to'ldiring",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}