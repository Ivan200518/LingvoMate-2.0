package com.example.lingvomatenew.domain.repository

import com.example.lingvomatenew.domain.model.Channel
import com.example.lingvomatenew.domain.model.Message
import kotlinx.coroutines.flow.StateFlow

interface ChatRepository {
    fun sendMessage(channelId: String, messageText : String)

    fun listenForMessages(channelId : String)

    val messages : StateFlow<List<Message>>

}