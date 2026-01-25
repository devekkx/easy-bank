package com.devekkx.easy_bank.modules.auth

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.devekkx.easy_bank.navigation.AuthMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$".toRegex()
    private val _uiState = MutableStateFlow(AuthUIState())
    val uiState: StateFlow<AuthUIState> = _uiState.asStateFlow()
    val authMode: AuthMode = savedStateHandle.get<AuthMode>("mode") ?: AuthMode.LOGIN


    //    input states
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var confirmPasswordError by mutableStateOf<String?>(null)

    //    Error states
    var nameError by mutableStateOf<String?>(null)
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)

//    val isConfirmValid = if (authMode == AuthMode.REGISTER) {
//        confirmPassword == password && confirmPassword.isNotEmpty()
//    } else true

    val passwordsMatch by derivedStateOf {
        password.isNotEmpty() && password == confirmPassword
    }


    //    Password validation check
    val hasUpperCase by derivedStateOf { password.any { it.isUpperCase() } }
    val hasLowerCase by derivedStateOf { password.any { it.isLowerCase() } }
    val hasDigit by derivedStateOf { password.any { it.isDigit() } }
    val hasSpecialCharacter by derivedStateOf { password.any { !it.isLetterOrDigit() } }
    val hasMinLength by derivedStateOf { password.length >= 8 }

    val isPasswordValid by derivedStateOf {
        hasUpperCase && hasLowerCase && hasDigit && hasMinLength && hasSpecialCharacter
    }

    val isSignUpFormValid: Boolean
        get() = nameError == null && emailError == null && passwordError == null && confirmPasswordError == null && isPasswordValid


    val isLoginFormValid: Boolean
        get() = password != "" && email != ""

    fun onNameChange(newName: String) {
        name = newName
        nameError = when {
            newName.isEmpty() -> null
            newName.length < 3 -> "Name must be at least 3 characters"
            else -> null
        }
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
        emailError = when {
            newEmail.isEmpty() -> null
            !android.util.Patterns.EMAIL_ADDRESS.matcher(newEmail).matches() -> "Invalid email"
            else -> null
        }
    }


    fun onPasswordChange(newPassword: String) {
        password = newPassword
        passwordError = when {
            newPassword.isEmpty() -> null
            newPassword.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }
    }

    fun onConfirmPasswordChange(newPassword: String) {
        confirmPassword = newPassword
        confirmPasswordError = if (passwordsMatch) {
            null
        } else if (confirmPassword.isEmpty()) {
            "Please confirm your password"
        } else {
            "Passwords do not match"
        }
    }

    fun handleAuth() {
        if (!isLoginFormValid) return
        Log.d("AUTH_MODE", authMode.name)
        Log.d("AUTH_DEBUG", "Logging in with email: $email and password: $password")

//        _uiState.update { it.copy(isLoading = true) }
        // Logic for authentication would go here
//        _uiState.update { it.copy(isLoggedIn = true, isLoading = false) }
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
