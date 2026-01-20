package com.devekkx.easy_bank.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    //Authentication flow
    @Serializable
    data object Login : Route

    @Serializable
    data object Register : Route

    @Serializable
    data object ForgotPassword : Route

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

