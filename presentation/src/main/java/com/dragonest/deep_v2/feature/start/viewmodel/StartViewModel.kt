package com.dragonest.deep_v2.feature.start.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dragonest.deep_v2.feature.start.state.GoogleLoginState
import com.dragonest.deep_v2.feature.start.state.LoginState
import com.dragonest.deep_v2.feature.start.state.SignupState
import com.dragonest.deep_v2.util.DeepApplication
import com.dragonest.domain.model.user.GoogleOauthRequestModel
import com.dragonest.domain.model.user.LoginRequestModel
import com.dragonest.domain.model.user.SignupRequestModel
import com.dragonest.domain.repository.UserRepository
import com.ggd.zendee.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {

    private val _loginState = MutableSharedFlow<LoginState>()
    val loginState: SharedFlow<LoginState> = _loginState

    private val _signupState = MutableSharedFlow<SignupState>()
    val signupState: SharedFlow<SignupState> = _signupState

    private val _googleLoginState = MutableSharedFlow<GoogleLoginState>()
    val googleLoginState: SharedFlow<GoogleLoginState> = _googleLoginState

    fun login(loginRequestModel: LoginRequestModel) = viewModelScope.launch {
        kotlin.runCatching {
            userRepository.login(loginRequestModel)
        }.onSuccess {
            Log.d(TAG, "login: SUCCESS!! $it")
            _loginState.emit(LoginState(isSuccess = true))
            DeepApplication.prefs.setTokensInLogin(it.token, it.refreshToken)
        }.onFailure { e ->
            Log.d(TAG, "login: FAILED.. $e")
            _loginState.emit(LoginState(error = "${e.message}"))
        }
    }

    fun signup(signupRequestModel: SignupRequestModel) = viewModelScope.launch {
        kotlin.runCatching {
            userRepository.signup(signupRequestModel)
        }.onSuccess {
            Log.d(TAG, "signup: SUCCESS!! $it")
            _signupState.emit(SignupState(isSuccess = true))
        }.onFailure { e ->
            Log.d(TAG, "login: FAILED.. $e")
            _signupState.emit(SignupState(error = "${e.message}"))
        }
    }

    fun googleLogin(googleOauthRequestModel: GoogleOauthRequestModel) = viewModelScope.launch {
        kotlin.runCatching {
            userRepository.googleLogin(googleOauthRequestModel)
        }.onSuccess {
            Log.d(TAG, "googleLogin: SUCCESS!! $it")
            _googleLoginState.emit(GoogleLoginState(isSuccess = true))
            DeepApplication.prefs.setTokensInLogin(it.token, it.refreshToken)
        }.onFailure {e ->
            Log.d(TAG, "googleLogin: FAILED.. $e")
            _googleLoginState.emit(GoogleLoginState(error = "$e"))
        }
    }

    companion object {
        private const val TAG = "StartViewModel"
    }
}