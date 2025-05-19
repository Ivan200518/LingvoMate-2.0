package com.example.lingvomatenew.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signIn(email: String, password: String) :Result<FirebaseUser?>

    suspend fun signUp(name : String, email: String, password: String) :Result<Unit>

    fun signOut()

    fun getCurrentUser() : FirebaseUser?
}