package com.devekkx.easy_bank.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {
    @Serializable
    data object Home: Route

    @Serializable
    data object About: Route

    @Serializable
    data object Contact: Route

    @Serializable
    data class Detail(val id: String): Route, NavKey
}

