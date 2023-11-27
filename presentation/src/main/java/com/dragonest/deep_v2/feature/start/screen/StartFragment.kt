package com.dragonest.deep_v2.feature.start.screen

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.FragmentStartBinding
import com.dragonest.deep_v2.feature.start.viewmodel.StartViewModel
import com.ggd.zendee.base.BaseFragment

class StartFragment: BaseFragment<FragmentStartBinding, StartViewModel>(R.layout.fragment_start) {

    override val viewModel: StartViewModel by viewModels()

    override fun start() {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(
                StartFragmentDirections.toLoginFragment()
            )
        }
        binding.linearSignup.setOnClickListener {
            findNavController().navigate(
                StartFragmentDirections.toSignupFragment()
            )
        }
    }

}