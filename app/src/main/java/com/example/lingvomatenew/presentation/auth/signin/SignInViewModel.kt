package com.example.lingvomatenew.presentation.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingvomatenew.domain.repository.AuthRepository
import com.example.lingvomatenew.presentation.auth.signup.SignUpState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SignInState>(SignInState.Nothing)
    val state = _state.asStateFlow()
    private val _errorMessagePassword = MutableStateFlow(false)
    val errorMessagePassword : StateFlow<Boolean> = _errorMessagePassword

    private val _errorMessageEmail = MutableStateFlow(false)
    val errorMessageEmail : StateFlow<Boolean> = _errorMessageEmail
    fun signIn( email: String, password: String) {
        if (!validateInput(email, password)) return

        viewModelScope.launch {
            _state.value = SignInState.Loading

            val result = authRepository.signIn( email, password)

            _state.value = if (result.isSuccess) {
                SignInState.Success
            } else {
                SignInState.Error
            }
        }
    }
    private fun validateInput(email: String, password: String): Boolean {
        var result = true
        if (email.isBlank() || password.isBlank()) {
            _errorMessageEmail.value = true
            result = false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errorMessageEmail.value = true
            result = false
        }
        if (password.length < 6) {
            _errorMessagePassword.value
            result = false
        }
        return result
    }


    fun resetErrorEmail() {
        _errorMessageEmail.value = false
    }


    fun resetErrorPassword() {
        _errorMessagePassword.value = false
    }
}


sealed class SignInState {
    object Nothing : SignInState()
    object Loading : SignInState()
    object Success : SignInState()
    object Error : SignInState()
}
