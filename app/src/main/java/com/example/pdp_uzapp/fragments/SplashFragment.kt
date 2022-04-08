package com.example.pdp_uzapp.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pdp_uzapp.R
import com.example.pdp_uzapp.databinding.FragmentSplashBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

  private val binding by viewBinding (FragmentSplashBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Handler(Looper.myLooper()!!).postDelayed({
                findNavController().navigate(R.id.homeFragment)
            }, 500)
        }
    }
}