package com.dragonest.deep_v2.feature.start.screen

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.FragmentStartBinding
import com.dragonest.deep_v2.feature.start.viewmodel.StartViewModel
import com.dragonest.deep_v2.util.DeepApplication
import com.ggd.zendee.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment: BaseFragment<FragmentStartBinding, StartViewModel>(R.layout.fragment_start) {

    override val viewModel: StartViewModel by viewModels()

    override fun start() {
//        if (DeepApplication.prefs.autoLogin) {
//            findNavController().navigate(StartFragmentDirections.toNavigationStorage())
//        }

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