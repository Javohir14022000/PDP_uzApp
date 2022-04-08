package com.example.pdp_uzapp.fragments.mentor_fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.adapters.RecAdapter2
import com.example.pdp_uzapp.adapters.RecAdapter3
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.FragmentListMentorBinding
import com.example.pdp_uzapp.entity.Mentor
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class ListMentorFragment : Fragment(R.layout.fragment_list_mentor) {

    private var course: Int = -1
    private val binding: FragmentListMentorBinding by viewBinding(FragmentListMentorBinding::bind)
    lateinit var list: List<Mentor>
    lateinit var recAdapter3: RecAdapter3
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course = it.getInt("course_mentor")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appDatabase = AppDatabase.getInstance(requireContext())
        binding.apply {
            list = appDatabase.mentorDao().getAllMentors(course) as ArrayList<Mentor>

            recAdapter3 = RecAdapter3(list, object : RecAdapter3.OnItemMentorClickListener {
                override fun onClickEdit(mentor: Mentor, position: Int) {

                }

                override fun onClickDelete(mentor: Mentor, position: Int) {

                }

            })

            add.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("course_add_mentor", course)
                findNavController().navigate(R.id.addMentorFragment, bundle)
            }

            rv.adapter = recAdapter3
        }


    }

}