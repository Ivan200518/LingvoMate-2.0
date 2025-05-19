package com.example.lingvomatenew.domain.repository

import com.example.lingvomatenew.domain.model.Channel
import kotlinx.coroutines.flow.StateFlow

interface HomeRepository {
    val channels : StateFlow<List<Channel>>
    suspend  fun addChannel(name : String)
    fun getChannels()
}