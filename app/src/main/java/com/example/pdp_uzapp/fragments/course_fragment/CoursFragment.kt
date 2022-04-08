package com.example.pdp_uzapp.fragments.course_fragment

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.adapters.RecAdapter
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.DialogCourseBinding
import com.example.pdp_uzapp.databinding.FragmentCoursBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import com.example.pdp_uzapp.entity.Course

class CoursFragment : Fragment(R.layout.fragment_cours) {

    private val binding by viewBinding(FragmentCoursBinding::bind)
    lateinit var appDatabase: AppDatabase
    lateinit var recAdapter: RecAdapter
    lateinit var list: List<Course>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appDatabase = AppDatabase.getInstance(requireContext())
        list = ArrayList(appDatabase.courseDao().getListCourse())

        recAdapter = RecAdapter(list, object : RecAdapter.OnClickMyListener {
            override fun onClick(course: Course) {
                val bundle = Bundle()
                bundle.putSerializable("course", course)
                findNavController().navigate(R.id.courseInfoFragment, bundle)
            }
        })

        binding.apply {
            rv.adapter = recAdapter
            addIcon.setOnClickListener {
                val dialog = AlertDialog.Builder(requireContext())
                val bindingDialog = DialogCourseBinding.inflate(layoutInflater)
                val create = dialog.create() as AlertDialog
                create.setView(bindingDialog.root)

                create.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                bindingDialog.apply {
                    addTv.setOnClickListener {
                        if (courseName.text.isNotEmpty() && courseAbout.text.isNotEmpty()) {
                            val course = Course(
                                name = courseName.text.toString(),
                                desc = courseAbout.text.toString()
                            )
                            appDatabase.courseDao().addCourse(course)
                            val list1 = ArrayList<Course>(appDatabase.courseDao().getListCourse())
                            (list as ArrayList<Course>).add(list1[list1.size-1])
                            recAdapter.notifyItemInserted(list.size)
                        }
                        create.dismiss()
                    }

                    close.setOnClickListener { create.dismiss() }
                }

                create.show()
            }
            exitIcon.setOnClickListener { findNavController().popBackStack() }
        }
    }
}


