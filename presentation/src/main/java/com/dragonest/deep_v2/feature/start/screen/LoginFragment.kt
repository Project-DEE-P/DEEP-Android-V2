package com.dragonest.deep_v2.feature.start.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.FragmentLoginBinding
import com.dragonest.deep_v2.feature.start.viewmodel.StartViewModel
import com.dragonest.deep_v2.util.showToast
import com.dragonest.domain.model.user.LoginRequestModel
import com.ggd.zendee.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding, StartViewModel>(R.layout.fragment_login) {

    override val viewModel: StartViewModel by viewModels()

    private lateinit var context: Context

    override fun start() {
        context = requireContext()

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        onClickLoginButton()
        handleLoginState()
    }

    private fun onClickLoginButton() {
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString().trim()
            val pwd = binding.etPwd.text.toString().trim()

            if (id.isEmpty() || pwd.isEmpty()) {
                showToast(context, "빈칸을 채워주세요")
            } else {
                viewModel.login(
                    LoginRequestModel(id, pwd)
                )
            }
        }
    }

    private fun handleLoginState() {
        lifecycleScope.launch {
            viewModel.loginState.collect {
                if (it.isSuccess) {
                    findNavController().navigate(LoginFragmentDirections.toNavigationStorage())
                }
                if (it.error.isNotEmpty()) {
                    showToast(context, it.error)
                }
            }
        }
    }

}