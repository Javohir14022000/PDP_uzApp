package com.example.pdp_uzapp.fragments.mentor_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.adapters.RecAdapter
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.FragmentMentorCoursBinding
import com.example.pdp_uzapp.entity.Course
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class MentorCoursFragment : Fragment(R.layout.fragment_mentor_cours) {

    private val binding: FragmentMentorCoursBinding by viewBinding(FragmentMentorCoursBinding::bind)
    lateinit var list: List<Course>
    lateinit var recAdapter: RecAdapter
    lateinit var appDatabase: AppDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appDatabase = AppDatabase.getInstance(requireContext())
        list = appDatabase.courseDao().getListCourse()

        binding.apply {
            recAdapter = RecAdapter(list, object : RecAdapter.OnClickMyListener{
                override fun onClick(course: Course) {
                    val bundle = Bundle()
                    bundle.putInt("course_mentor", course.id)
                    findNavController().navigate(R.id.listMentorFragment, bundle)
                }

            })
            rv.adapter = recAdapter
        }
    }


}