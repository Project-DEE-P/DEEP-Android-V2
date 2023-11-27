package com.dragonest.deep_v2.feature.start.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.FragmentSignupBinding
import com.dragonest.deep_v2.feature.start.viewmodel.StartViewModel
import com.ggd.zendee.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment: BaseFragment<FragmentSignupBinding, StartViewModel>(R.layout.fragment_signup) {

    override val viewModel: StartViewModel by viewModels()

    override fun start() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

}