package com.example.pdp_uzapp.fragments.group_fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.adapters.RecAdapter1
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.DialogDeleteBinding
import com.example.pdp_uzapp.databinding.FragmentNoActiveGroupBinding
import com.example.pdp_uzapp.entity.Course
import com.example.pdp_uzapp.entity.Group
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class NoActiveGroupFragment : Fragment(R.layout.fragment_no_active_group) {
    var course: Int = -1
    private val binding: FragmentNoActiveGroupBinding by viewBinding(FragmentNoActiveGroupBinding::bind)
    lateinit var recAdapter1: RecAdapter1
    lateinit var appDatabase: AppDatabase
    lateinit var listGroup: ArrayList<Group>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course = it.getInt("course_group")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appDatabase = AppDatabase.getInstance(requireContext())
        binding.apply {
            listGroup = ArrayList()
            listGroup = appDatabase.groupDao().getGroups(course) as ArrayList<Group>

            recAdapter1 = RecAdapter1(listGroup, object : RecAdapter1.OnClickMyListener {
                override fun onclickShow(group: Group) {

                    val bundle = Bundle()
                    bundle.putSerializable("group", group.id)
                    findNavController().navigate(R.id.listStudentFragment, bundle)
                }

                override fun onClickDelete(group: Group, position: Int) {

                    val dialog = AlertDialog.Builder(requireContext())
                    val bindingDialog = DialogDeleteBinding.inflate(layoutInflater)
                    val create = dialog.create()
                    create.setView(bindingDialog.root)
                    bindingDialog.apply {
                        accept.setOnClickListener {
                            appDatabase.groupDao().deleteGroup(group)
                            recAdapter1.notifyItemRemoved(position)
                            recAdapter1.notifyItemChanged(position)
                            create.dismiss()
                        }
                        close.setOnClickListener { create.dismiss() }
                    }
                    create.show()

                }

                override fun onClickEdit(group: Group, position: Int) {

                }

            })

            rv.adapter = recAdapter1
        }

    }

}