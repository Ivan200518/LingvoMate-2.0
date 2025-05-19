package com.example.lingvomatenew.domain.use_case

import com.example.lingvomatenew.data.repository.HomeRepositoryImpl
import javax.inject.Inject

class AddChannelUseCase @Inject constructor(private val homeRepository: HomeRepositoryImpl) {
    suspend fun invoke(name: String) {
        homeRepository.addChannel(name)
    }
}