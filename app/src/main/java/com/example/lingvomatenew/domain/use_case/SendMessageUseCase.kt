package com.example.lingvomatenew.domain.use_case

import com.example.lingvomatenew.data.repository.ChatRepositoryImpl
import com.example.lingvomatenew.domain.model.Message
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val chatRepositoryImpl: ChatRepositoryImpl){
    fun invoke(channelID: String, messageText: String) {
        chatRepositoryImpl.sendMessage(channelID ,messageText)
    }
}