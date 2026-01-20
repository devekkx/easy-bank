package com.devekkx.easy_bank.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.devekkx.easy_bank.screens.LoginScreen
import com.devekkx.easy_bank.screens.RegisterScreen
import com.devekkx.easy_bank.services.auth.AuthViewModel


@Composable
fun NavigationController(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel(),
) {
//    Observe authentication flow
    val authState by authViewModel.uiState.collectAsStateWithLifecycle()

//    configure backStack (stateful list)
    val backStack =
        remember { mutableStateListOf<Route>(if (authState.isLoggedIn) Route.Dashboard else Route.Login) }

//    Reactive navigation: Switch stacks when login status changes
    LaunchedEffect(authState.isLoggedIn) {
        backStack.clear()
        if (authState.isLoggedIn) {
            backStack.add(Route.Dashboard)
        } else {
            backStack.add(Route.Login)

        }
    }

//    Render the screens using NavDisplay
    NavDisplay(
        backStack = backStack,
//        onBack = { if (backStack.size > 1) backStack.removeLast() }
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
//                Auth Flow
                is Route.Login -> NavEntry(key) {
                    LoginScreen(
                        onLoginSuccess = { authViewModel.login() },
                        onSignupClick = { backStack.add(Route.Register) },
//                        onForgotClick = { backStack.add(Route.ForgotPassword) }
                    )
                }

                is Route.Register -> NavEntry(key) {
                    RegisterScreen()
                }

                is Route.ForgotPassword -> NavEntry(key) {
                    RegisterScreen()
//                    ForgotPasswordScreen()
                }

//            Main App Flow
                is Route.Dashboard -> NavEntry(key) {
//                    DashboardScreen(
//                        onLogout = { authViewModel.logout() },
//                        onTransactionClick = { id -> backStack.add(Route.TransactionDetail(id)) },
//                        onTransferClick = { backStack.add(Route.Transfer) }
//                    )
                }

                is Route.TransactionDetail -> NavEntry(key) {
                    RegisterScreen()
                    RegisterScreen()
//                    TransactionDetailScreen(txId = key.txId)
                }

                is Route.Transfer -> NavEntry(key) {
                    RegisterScreen()
//                    TransferScreen(onSuccess = {
//                        // Return to Dashboard and clear the transfer from stack
//                        backStack.removeAll { it !is Route.Dashboard }
//                    })
                }
                // Handle remaining Route.ChangePassword if needed
                else -> NavEntry(key) { Text("Page not found") }
            }
        }
    )
}