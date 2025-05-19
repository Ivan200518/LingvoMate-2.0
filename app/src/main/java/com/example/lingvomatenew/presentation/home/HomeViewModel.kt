package com.example.lingvomatenew.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingvomatenew.data.repository.HomeRepositoryImpl
import com.example.lingvomatenew.domain.repository.HomeRepository
import com.example.lingvomatenew.domain.use_case.AddChannelUseCase
import com.example.lingvomatenew.domain.use_case.GetChannelsUseCase
import com.example.lingvomatenew.presentation.auth.signin.SignInState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getChannelsUseCase: GetChannelsUseCase,
    private val addChannelUseCase: AddChannelUseCase,
    private val homeRepository: HomeRepository
): ViewModel(){

    val channels = homeRepository.channels

    private val _state = MutableStateFlow<HomeState>(HomeState.Nothing)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getChannels()
        }
    }
    fun signOut() {
        FirebaseAuth.getInstance().signOut()
        _state.value = HomeState.SuccessLogOut
    }

    fun resetHomeState() {
        _state.value = HomeState.Nothing
    }

    fun addChannel(name: String) {
        viewModelScope.launch {
            addChannelUseCase.invoke(name)
        }
    }

    fun getChannels() {
        getChannelsUseCase.invoke()
    }
}



sealed class HomeState {
    object Nothing : HomeState()
    object Loading : HomeState()
    object SuccessLogOut : HomeState()
    object Error : HomeState()
}
