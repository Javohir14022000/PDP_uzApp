package com.example.pdp_uzapp.fragments.group_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.adapters.RecAdapter
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.FragmentGroupBinding
import com.example.pdp_uzapp.entity.Course
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class GroupFragment : Fragment(R.layout.fragment_group) {

    lateinit var appDatabase: AppDatabase
    lateinit var list: List<Course>
    lateinit var recAdapter: RecAdapter

    private val binding by viewBinding(FragmentGroupBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appDatabase = AppDatabase.getInstance(requireContext())
        list = ArrayList(appDatabase.courseDao().getListCourse())
        binding.apply {
            recAdapter = RecAdapter(list, object : RecAdapter.OnClickMyListener {
                override fun onClick(course: Course) {
                    val bundle = Bundle()
                    bundle.putInt("course_group", course.id)
                    findNavController().navigate(R.id.viewPagerFragment, bundle)
                }

            })
            rv.adapter = recAdapter
        }
    }

}