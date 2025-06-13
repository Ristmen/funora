package com.pb.funora.auth.data

import com.google.firebase.auth.FirebaseAuth
import com.pb.funora.auth.model.AuthResult
import com.pb.funora.auth.model.UserCredentials
import kotlinx.coroutines.tasks.await

actual class AuthService {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    actual suspend fun signIn(credentials: UserCredentials): AuthResult = try {
        auth.signInWithEmailAndPassword(credentials.email, credentials.password).await()
        AuthResult.Success
    } catch (e: Exception) {
        AuthResult.Failure(e.message ?: "Unknown error")
    }

    actual suspend fun signUp(credentials: UserCredentials): AuthResult = try {
        auth.createUserWithEmailAndPassword(credentials.email, credentials.password).await()
        AuthResult.Success
    } catch (e: Exception) {
        AuthResult.Failure(e.message ?: "Unknown error")
    }

    actual suspend fun signOut(): AuthResult = try {
        auth.signOut()
        AuthResult.Success
    } catch (e: Exception) {
        AuthResult.Failure(e.message ?: "Unknown error")
    }
}
