package com.devekkx.easy_bank.modules.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUIState())
    val uiState: StateFlow<AuthUIState> = _uiState.asStateFlow()

    var email by mutableStateOf("")
        private set
    
    var emailError by mutableStateOf<String?>(null)
        private set
        
    var password by mutableStateOf("")
        private set

    val isFormValid: Boolean
        get() = email.contains("@") && password.length >= 6

    fun onEmailChange(newEmail: String) {
        email = newEmail
        emailError = when {
            newEmail.isEmpty() -> null
            !newEmail.contains("@") -> "Invalid email"
            else -> null
        }
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun login() {
        if (!isFormValid) return
        
        _uiState.update { it.copy(isLoading = true) }
        // Logic for authentication would go here
        _uiState.update { it.copy(isLoggedIn = true, isLoading = false) }
    }

    fun logout() {
        _uiState.update { it.copy(isLoggedIn = false) }
    }
}

data class AuthUIState(
    val isLoggedIn: Boolean = false,
    val userName: String? = null,
    val isLoading: Boolean = false
)
