package com.dragonest.deep_v2.feature.start.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.FragmentSignupBinding
import com.dragonest.deep_v2.feature.start.viewmodel.StartViewModel
import com.dragonest.deep_v2.util.showToast
import com.dragonest.domain.model.user.LoginRequestModel
import com.dragonest.domain.model.user.SignupRequestModel
import com.ggd.zendee.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment: BaseFragment<FragmentSignupBinding, StartViewModel>(R.layout.fragment_signup) {

    override val viewModel: StartViewModel by viewModels()

    private lateinit var context: Context

    override fun start() {
        context = requireContext()

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val id = binding.etId.text.toString().trim()
            val pwd = binding.etPwd.text.toString().trim()
            val pwdCheck = binding.etPwdCheck.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            if (name.isEmpty() || id.isEmpty() || pwd.isEmpty() || pwdCheck.isEmpty() || email.isEmpty()) {
                showToast(context, "빈칸을 채워주세요")
            } else if (pwd != pwdCheck) {
                showToast(context, "비밀번호를 동일하게 입력해주세요")
            } else {
                viewModel.signup(
                    SignupRequestModel(id, pwd, name, email)
                )
            }
        }

        lifecycleScope.launch {
            viewModel.signupState.collect {
                if (it.isSuccess) {
                    showToast(context, "가입한 아이디와 비밀번호로 로그인해주세요")
                    findNavController().navigate(SignupFragmentDirections.toLoginFragment())
                }
                if (it.error.isNotEmpty()) {
                    showToast(context, it.error)
                }
            }
        }
    }

}