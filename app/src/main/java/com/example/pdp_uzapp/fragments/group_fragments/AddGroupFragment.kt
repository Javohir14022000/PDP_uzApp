package com.example.pdp_uzapp.fragments.group_fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.adapters.SpinnerAdapterMentor
import com.example.pdp_uzapp.database.AppDatabase
import com.example.pdp_uzapp.databinding.FragmentAddGroupBinding
import com.example.pdp_uzapp.entity.Course
import com.example.pdp_uzapp.entity.Group
import com.example.pdp_uzapp.entity.Mentor
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.pdp.pdpapp.adapters.SpinnerAdapterTime

class AddGroupFragment : Fragment(R.layout.fragment_add_group) {

    private var course: Int = -1
    private val binding: FragmentAddGroupBinding by viewBinding(FragmentAddGroupBinding::bind)
    lateinit var appDatabase: AppDatabase
    lateinit var group: Group
    lateinit var listMentor: List<Mentor>
    lateinit var list: List<Group>
    lateinit var spinnerAdapterMentor: SpinnerAdapterMentor
    lateinit var spinnerAdapterTime1: SpinnerAdapterTime
    lateinit var spinnerAdapterTime2: SpinnerAdapterTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            course = it.getInt("course_group", 1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            appDatabase = AppDatabase.getInstance(requireContext())

            listMentor = appDatabase.mentorDao().getAllMentors(courseId = course)
            val listTime = listOf("9:00-11:00", "19:00-21:00")
            val listDate = listOf("toq", "juft")
            spinnerAdapterMentor = SpinnerAdapterMentor(listMentor)
            spinnerAdapterTime1 = SpinnerAdapterTime(listTime)
            spinnerAdapterTime2 = SpinnerAdapterTime(listDate)


            spinnerMentor.adapter = spinnerAdapterMentor
            spinnerDay.adapter = spinnerAdapterTime2
            spinnerTime.adapter = spinnerAdapterTime1

            save.setOnClickListener {
                val groupName = groupName.text
                val mentor = listMentor[spinnerMentor.selectedItemPosition]
                val time = listTime[spinnerTime.selectedItemPosition]
                val date = listDate[spinnerDay.selectedItemPosition]
                if (groupName.isNullOrEmpty() && mentor.firstname.isNotEmpty() && time.isNotEmpty() && date.isNotEmpty()) {
                    val group = Group(
                        name = groupName.toString(),
                        mentorId = mentor.id,
                        isOpen = true,
                        time = time,
                        date = date,
                        courseId = course
                    )
                    appDatabase.groupDao().addGroup(group)
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Maydonlar to'liq to'ldirilmagan ",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }


        }
    }
}