package com.dragonest.deep_v2.feature.start.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.FragmentStartBinding
import com.dragonest.deep_v2.feature.start.viewmodel.StartViewModel
import com.dragonest.deep_v2.util.DeepApplication
import com.dragonest.deep_v2.util.GOOGLE_CLIENT_ID
import com.dragonest.deep_v2.util.showToast
import com.dragonest.domain.model.user.GoogleOauthRequestModel
import com.ggd.zendee.base.BaseFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartFragment: BaseFragment<FragmentStartBinding, StartViewModel>(R.layout.fragment_start) {

    override val viewModel: StartViewModel by viewModels()

    private lateinit var context: Context

    private lateinit var googleToken: String

    private val googleAuthLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        kotlin.runCatching {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)

            googleToken = account.idToken.toString()
        }.onSuccess {
            viewModel.googleLogin(GoogleOauthRequestModel(googleToken))
            Log.d(TAG, "Google Oauth Success!! $googleToken")
            Toast.makeText(requireContext(), "로그인이 되었습니다", Toast.LENGTH_SHORT).show()
        }.onFailure { e ->
            Log.e(TAG, "Google Oauth Failed.." + e.stackTraceToString())
            Toast.makeText(requireContext(), "로그인에 실패했습니다", Toast.LENGTH_SHORT).show()
        }
    }

    private val googleSignInClient: GoogleSignInClient by lazy { getGoogleClient() }

    override fun start() {
        context = requireContext()

        if (DeepApplication.prefs.autoLogin) {
            findNavController().navigate(StartFragmentDirections.toNavigationStorage())
        }

        onClickButtons()
        handleGoogleLoginState()
    }

    private fun onClickButtons() {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(
                StartFragmentDirections.toLoginFragment()
            )
        }
        binding.btnGoogle.setOnClickListener {
            requestGoogleLogin()
        }
        binding.linearSignup.setOnClickListener {
            findNavController().navigate(
                StartFragmentDirections.toSignupFragment()
            )
        }
    }

    private fun handleGoogleLoginState() {
        lifecycleScope.launch {
            viewModel.googleLoginState.collect {
                if (it.isSuccess) {
                    findNavController().navigate(StartFragmentDirections.toNavigationStorage())
                }
                if (it.error.isNotEmpty()) {
                    showToast(context, it.error)
                }
            }
        }
    }

    private fun requestGoogleLogin() {
        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        googleAuthLauncher.launch(signInIntent)
    }

    private fun getGoogleClient(): GoogleSignInClient {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(GOOGLE_CLIENT_ID)
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(requireActivity(), googleSignInOption)
    }

    companion object {
        private const val TAG = "StartFragment"
    }
}