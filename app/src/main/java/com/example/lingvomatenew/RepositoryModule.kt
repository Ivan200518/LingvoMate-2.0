package com.example.lingvomatenew

import com.example.lingvomatenew.data.repository.AuthRepositoryImpl
import com.example.lingvomatenew.data.repository.ChatRepositoryImpl
import com.example.lingvomatenew.data.repository.HomeRepositoryImpl
import com.example.lingvomatenew.domain.repository.AuthRepository
import com.example.lingvomatenew.domain.repository.ChatRepository
import com.example.lingvomatenew.domain.repository.HomeRepository
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.Binds
import dagger.Module


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository


    @Binds
    @Singleton
    abstract fun bindChatRepository(
        chatRepositoryImpl: ChatRepositoryImpl
    ): ChatRepository



    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository
}