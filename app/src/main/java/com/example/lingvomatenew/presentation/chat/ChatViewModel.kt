package com.example.lingvomatenew.presentation.chat

import com.example.lingvomatenew.data.repository.ChatRepositoryImpl
import com.example.lingvomatenew.domain.repository.ChatRepository
import com.example.lingvomatenew.domain.use_case.ListenForMessagesUseCase
import com.example.lingvomatenew.domain.use_case.SendMessageUseCase

class ChatViewModel(
    private val sendMessageUseCase : SendMessageUseCase,
    private val listenForMessagesUseCase: ListenForMessagesUseCase
) {
    val messages = listenForMessagesUseCase.messages

    fun sendMessage(channelId: String, messageText: String) {
        sendMessageUseCase.invoke(channelId, messageText)
    }

    fun startListening(channelId: String) {
        listenForMessagesUseCase.invoke(channelId)
    }
}