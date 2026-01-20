package com.devekkx.easy_bank.services.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel : ViewModel() {
    // Internal state (private to prevent external modification)
    private val _uiState = MutableStateFlow(AuthUIState())

    // External state (read-only for the UI)
    val uiState: StateFlow<AuthUIState> = _uiState.asStateFlow()

    fun login() {
        // Logic for authentication
        _uiState.update { it.copy(isLoggedIn = true) }
    }

    fun logout() {
        // Logic for clearing session
        _uiState.update { it.copy(isLoggedIn = false) }
    }
}


data class AuthUIState(
    val isLoggedIn: Boolean = false,
    val userName: String? = null,
    val isLoading: Boolean = false
)