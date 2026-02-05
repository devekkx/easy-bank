package com.devekkx.easy_bank.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.devekkx.easy_bank.modules.auth.AuthViewModel
import com.devekkx.easy_bank.screens.AuthScreen
import com.devekkx.easy_bank.screens.ForgotPasswordScreen


@Composable
fun MainNavigation(
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val authState by authViewModel.uiState.collectAsStateWithLifecycle()

//    configure backStack (stateful list)
    val backStack = remember {
        mutableStateListOf<Route>(
            if (authState.isLoggedIn) Route.Dashboard else Route.Auth(AuthMode.LOGIN)
        )
    }

//    Reactive navigation: Switch stacks when login status changes
    LaunchedEffect(authState.isLoggedIn) {
        backStack.clear()
        if (authState.isLoggedIn) {
            backStack.add(Route.Dashboard)
        } else {
            backStack.add(Route.Auth(AuthMode.LOGIN))

        }
    }

//    Render the screens using NavDisplay
    NavDisplay(
        backStack = backStack,
//        onBack = { if (backStack.size > 1) backStack.removeLast() }
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { route ->
            when (route) {
//                Auth Flow
                is Route.Auth -> NavEntry(route) {
                    AuthScreen(
                        mode = route.mode,
//                        viewModel = hiltViewModel(key = route.mode.name),
                        onAuthActionClick = {
                            // Toggle between Login and Register
                            val newMode =
                                if (route.mode == AuthMode.LOGIN) AuthMode.REGISTER else AuthMode.LOGIN
                            backStack.removeAt(backStack.lastIndex)
                            backStack.add(Route.Auth(newMode))
                        },
                        onForgotClick = { backStack.add(Route.ForgotPassword(mode = AuthMode.FORGOT_PASSWORD)) },
                    )
                }

                is Route.ForgotPassword -> NavEntry(route) {
                    ForgotPasswordScreen(
                        mode = route.mode,
                        viewModel = hiltViewModel(key = route.mode.name),
                        onBackClick = { backStack.removeLastOrNull() },
                        onHandleForgotPassword = {
                            when (route.mode) {
                                AuthMode.CHANGE_PASSWORD -> {
                                    backStack.add(Route.ForgotPassword(mode = AuthMode.CHANGE_PASSWORD))
                                }

                                AuthMode.CONFIRM_CODE -> {
                                    backStack.add(Route.ForgotPassword(mode = AuthMode.CHANGE_PASSWORD))
                                }

                                else -> backStack.add(Route.ForgotPassword(mode = AuthMode.FORGOT_PASSWORD))
                            }
//                            backStack.removeLastOrNull()
//                            backStack.add(Route.Auth(mode = AuthMode.LOGIN))
                        }
                    )
                }

                is Route.Dashboard -> NavEntry(route) {
                    // DashboardScreen() logic...
                }

                else -> NavEntry(route) {
                    AuthScreen(
                        mode = AuthMode.LOGIN,
                        onAuthActionClick = {}, onForgotClick = {})
                }
            }
        }
    )
}