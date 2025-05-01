package com.example.lingvomatenew.domain.model

data class Channel(
    val id : String,
    val text : String,
    val createAt : Long = System.currentTimeMillis()
)