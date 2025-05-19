package com.example.lingvomatenew.presentation.chat

import androidx.lifecycle.ViewModel
import com.example.lingvomatenew.data.repository.ChatRepositoryImpl
import com.example.lingvomatenew.domain.repository.ChatRepository
import com.example.lingvomatenew.domain.use_case.ListenForMessagesUseCase
import com.example.lingvomatenew.domain.use_case.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase : SendMessageUseCase,
    private val listenForMessagesUseCase: ListenForMessagesUseCase
) : ViewModel(){
    val messages = listenForMessagesUseCase.messages

    fun sendMessage(channelId: String, messageText: String) {
        sendMessageUseCase.invoke(channelId, messageText)
    }

    fun startListening(channelId: String) {
        listenForMessagesUseCase.invoke(channelId)
    }
}