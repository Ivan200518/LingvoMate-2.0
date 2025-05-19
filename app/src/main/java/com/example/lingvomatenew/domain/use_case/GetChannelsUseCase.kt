package com.example.lingvomatenew.domain.use_case

import com.example.lingvomatenew.data.repository.HomeRepositoryImpl
import com.example.lingvomatenew.domain.repository.HomeRepository
import javax.inject.Inject

class GetChannelsUseCase @Inject constructor(
    private val repository : HomeRepository
){
    fun invoke() {
        repository.getChannels()
    }
}