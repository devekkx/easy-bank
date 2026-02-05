package com.devekkx.easy_bank.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
enum class AuthMode { LOGIN, REGISTER, FORGOT_PASSWORD, CONFIRM_CODE, CHANGE_PASSWORD }

@Serializable
sealed interface Route : NavKey {
    //Authentication flow
    @Serializable
    data class Auth(val mode: AuthMode) : Route

    @Serializable
    data class ForgotPassword(val mode: AuthMode) : Route

    @Serializable
    data object ChangePassword : Route

    //Main App flow
    @Serializable
    data object Dashboard : Route

    @Serializable
    data class TransactionDetail(val txId: String) : Route

    @Serializable
    data object Transfer : Route
}

