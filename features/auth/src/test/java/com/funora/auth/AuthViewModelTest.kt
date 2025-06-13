package com.funora.auth

import com.google.firebase.auth.FirebaseAuth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {
    private lateinit var repository: AuthRepository
    private lateinit var viewModel: AuthViewModel

    @Before
    fun setup() {
        val firebase = mockk<FirebaseAuth>(relaxed = true)
        repository = AuthRepository(firebase)
        viewModel = AuthViewModel(repository)
    }

    @Test
    fun emailChangeUpdatesState() = runTest {
        viewModel.onEmailChange("test@example.com")
        assertEquals("test@example.com", viewModel.loginState.value.email)
    }
}
