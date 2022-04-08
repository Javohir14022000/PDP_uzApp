package com.example.pdp_uzapp.fragments.group_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.adapters.RecAdapter1
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.FragmentActiveGroupBinding
import com.example.pdp_uzapp.entity.Course
import com.example.pdp_uzapp.entity.Group
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class ActiveGroupFragment : Fragment(R.layout.fragment_active_group) {
    private val binding: FragmentActiveGroupBinding by viewBinding(FragmentActiveGroupBinding::bind)
    lateinit var recAdapter1: RecAdapter1
    lateinit var appDatabase: AppDatabase
    private lateinit var course: Course
    private lateinit var list: List<Group>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course = it.getInt("course_group",0) as Course
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appDatabase = AppDatabase.getInstance(requireContext())

        list = ArrayList(appDatabase.groupDao().getGroups())
        binding.apply {
            recAdapter1 = RecAdapter1(list, object : RecAdapter1.OnClickMyListener {
                override fun onclickShow(group: Group) {

                }

                override fun onClickDelete(group: Group, position: Int) {

                }

                override fun onClickEdit(group: Group, position: Int) {

                }

            })

            rv.adapter = recAdapter1
        }

    }
}