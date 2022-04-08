package com.example.pdp_uzapp.fragments.group_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.pdp_uzapp.databinding.FragmentListAddStudentBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class ListAddStudentFragment : Fragment() {
    private val binding by viewBinding(FragmentListAddStudentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {  }
    }

}