package com.dragonest.deep_v2.feature.start.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dragonest.deep_v2.feature.start.state.LoginState
import com.dragonest.deep_v2.util.DeepApplication
import com.dragonest.domain.model.user.LoginRequestModel
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

    companion object {
        private const val TAG = "StartViewModel"
    }
}