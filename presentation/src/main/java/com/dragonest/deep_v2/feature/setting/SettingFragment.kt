package com.dragonest.deep_v2.feature.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.FragmentSettingBinding
import com.dragonest.deep_v2.util.DeepApplication
import com.ggd.zendee.base.BaseFragment

class SettingFragment: BaseFragment<FragmentSettingBinding, SettingViewModel>(R.layout.fragment_setting) {

    override val viewModel: SettingViewModel by viewModels()

    override fun start() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnLogout.setOnClickListener {
            DeepApplication.prefs.deleteToken()
            findNavController().navigate(SettingFragmentDirections.toStartFragment())
        }
    }

}