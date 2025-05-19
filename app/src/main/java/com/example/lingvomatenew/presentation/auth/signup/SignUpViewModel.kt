package com.example.lingvomatenew.presentation.auth.signup

import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.lingvomatenew.domain.repository.AuthRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow<SignUpState>(SignUpState.Nothing)
    val state = _state.asStateFlow()

    private val _errorMessagePassword = MutableStateFlow(false)
    val errorMessagePassword : StateFlow<Boolean> = _errorMessagePassword

    private val _errorMessageEmail = MutableStateFlow(false)
    val errorMessageEmail : StateFlow<Boolean> = _errorMessageEmail


    private val _errorMessageName = MutableStateFlow(false)
    val errorMessageName : StateFlow<Boolean> = _errorMessageName


    fun signUp(name: String, email: String, password: String) {
        if (!validateInput(name, email, password)) return

        viewModelScope.launch {
            _state.value = SignUpState.Loading

            val result = authRepository.signUp(name, email, password)

            _state.value = if (result.isSuccess) {
                SignUpState.Success
            } else {
                SignUpState.Error
            }
        }
    }


    private fun validateInput(name: String, email: String, password: String): Boolean {
        var result = true

        if (name.isBlank() || name.length < 2 || !name.matches(Regex("^[A-Za-zА-Яа-яёЁ]+$"))) {
            result = false
            _errorMessageName.value = true
        }

        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            result = false
            _errorMessageEmail.value = true
        }

        val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$")
        if (password.isBlank() || !password.matches(passwordRegex)) {
            result = false
            _errorMessagePassword.value = true
        }

        return result
    }



    fun resetErrorEmail() {
        _errorMessageEmail.value = false
    }


    fun resetErrorPassword() {
        _errorMessagePassword.value = false
    }

    fun resetErrorName() {
        _errorMessageName.value = false
    }

}



sealed class SignUpState{
    object Nothing : SignUpState()
    object Loading : SignUpState()
    object Success : SignUpState()
    object Error : SignUpState()
}