package com.example.lingvomatenew.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.lingvomatenew.presentation.home.HomeState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel(){
    private val _state = MutableStateFlow<ProfileState>(ProfileState.Nothing)
    val state = _state.asStateFlow()

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
        _state.value = ProfileState.SuccessLogOut
    }

    fun resetProfileState() {
        _state.value = ProfileState.Nothing
    }

}


sealed class ProfileState {
    object Nothing : ProfileState()
    object Loading : ProfileState()
    object SuccessLogOut : ProfileState()
    object Error : ProfileState()
}
