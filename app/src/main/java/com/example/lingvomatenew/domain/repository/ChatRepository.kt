package com.example.lingvomatenew.domain.repository

import com.example.lingvomatenew.domain.model.Message

interface ChatRepository {
    fun sendMessage(message: Message)

    fun sendAudioMessage(message: Message)

    fun startAudioCall()

    fun finishAudioCall()

    fun sharePhoto()

    fun deletePhoto()

    fun sendPhoto()

}