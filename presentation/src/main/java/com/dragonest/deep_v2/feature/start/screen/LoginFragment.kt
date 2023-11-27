package com.dragonest.deep_v2.feature.start.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.FragmentLoginBinding
import com.dragonest.deep_v2.feature.start.viewmodel.StartViewModel
import com.ggd.zendee.base.BaseFragment

class LoginFragment: BaseFragment<FragmentLoginBinding, StartViewModel>(R.layout.fragment_login) {

    override val viewModel: StartViewModel by viewModels()

    override fun start() {

    }

}