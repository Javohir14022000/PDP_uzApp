package com.example.pdp_uzapp.fragments.group_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.adapters.RecAdapter2
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.FragmentListStudentBinding
import com.example.pdp_uzapp.entity.Group
import com.example.pdp_uzapp.entity.Student
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class ListStudentFragment : Fragment(R.layout.fragment_list_student) {

    private val binding: FragmentListStudentBinding by viewBinding(FragmentListStudentBinding::bind)
    lateinit var appDatabase: AppDatabase
    lateinit var group: Group
    lateinit var list: List<Student>
    lateinit var recAdapter2: RecAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            group = it.putInt("group", 0) as Group
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appDatabase = AppDatabase.getInstance(requireContext())
        list = ArrayList(appDatabase.studentDao().getStudentsByGroupId(group.id))

        binding.apply {

            recAdapter2 = RecAdapter2(list, object : RecAdapter2.OnItemClickListener{
                override fun editClick(student: Student, position: Int) {

                }

                override fun deleteClick(student: Student, position: Int) {

                }

            })

            rv.adapter = recAdapter2
        }



    }

}