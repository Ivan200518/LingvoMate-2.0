package com.example.lingvomatenew.domain.use_case

import com.example.lingvomatenew.data.repository.ChatRepositoryImpl
import com.example.lingvomatenew.domain.repository.ChatRepository
import javax.inject.Inject

class ListenForMessagesUseCase @Inject constructor(private val chatRepository: ChatRepository)
{
    val messages = chatRepository.messages
    fun invoke(channelID: String) {
        chatRepository.listenForMessages(channelID)
    }
}