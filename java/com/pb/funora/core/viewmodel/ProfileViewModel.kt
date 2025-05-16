package com.pb.funora.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.pb.funora.core.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val db = FirebaseFirestore.getInstance()

    /**
     * Kullanıcı bilgilerini Firestore'dan alır.
     */
    fun fetchUserData(userId: String) {
        viewModelScope.launch {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    val user = document.toObject(User::class.java)
                    _user.value = user
                }
                .addOnFailureListener {
                    _user.value = null
                }
        }
    }

    /**
     * Kullanıcı bilgilerini günceller.
     */
    fun updateUserData(userId: String, firstName: String, lastName: String, username: String, email: String) {
        val updatedUser = mapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "username" to username,
            "email" to email
        )

        db.collection("users").document(userId).update(updatedUser)
            .addOnSuccessListener {
                // Başarılı güncelleme işlemi
            }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
            }
    }
}
