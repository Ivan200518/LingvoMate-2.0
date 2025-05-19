package com.example.lingvomatenew.data.repository

import com.example.lingvomatenew.domain.model.Channel
import com.example.lingvomatenew.domain.repository.HomeRepository
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor() : HomeRepository{

    private val firebaseDatabase = Firebase.database
    private val _channels = MutableStateFlow<List<Channel>>(emptyList())
    override val channels: StateFlow<List<Channel>> = _channels.asStateFlow()

    override  fun getChannels() {
        firebaseDatabase.getReference("channel").get().addOnSuccessListener {
            val list = it.children.map { data ->
                Channel(data.key!!, data.value.toString())
            }
            _channels.value = list
        }
    }

    override suspend fun addChannel(name: String) {
        val key = firebaseDatabase.getReference("channel").push().key ?: return
        firebaseDatabase.getReference("channel").child(key).setValue(name).addOnSuccessListener {
            getChannels()
        }
    }
}