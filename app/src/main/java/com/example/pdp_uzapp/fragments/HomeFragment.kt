package com.example.pdp_uzapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.FragmentHomeBinding
import com.example.pdp_uzapp.entity.Mentor
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var mentor: Mentor
    private lateinit var appDatabase: AppDatabase
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appDatabase = AppDatabase.getInstance(requireContext())
        binding.apply {
            courses.setOnClickListener {
                findNavController().navigate(R.id.coursFragment)
            }
            groups.setOnClickListener {

                if (appDatabase.mentorDao().getListMentors().isNotEmpty()) {
                    findNavController().navigate(R.id.groupFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Iltimos oldin metor qo`shib oling",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            mentors.setOnClickListener {
                if (appDatabase.courseDao().getListCourse().isNotEmpty()) {
                    findNavController().navigate(R.id.mentorCoursFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Iltimos oldin kurs qo`shing",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}