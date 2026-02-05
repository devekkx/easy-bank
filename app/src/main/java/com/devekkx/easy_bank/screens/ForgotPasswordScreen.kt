package com.devekkx.easy_bank.screens

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.LocalActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devekkx.easy_bank.modules.auth.AuthViewModel
import com.devekkx.easy_bank.modules.auth.components.ForgotPasswordForm
import com.devekkx.easy_bank.navigation.AuthMode
import com.devekkx.easy_bank.ui.theme.White

@Preview
@Composable
fun ForgotPasswordScreen(
    onBackClick: () -> Unit = {},
    onHandleForgotPassword: () -> Unit = {},
    viewModel: AuthViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    mode: AuthMode = AuthMode.FORGOT_PASSWORD
) {
    val activity = LocalActivity.current as? ComponentActivity
    val title = when (mode) {
        AuthMode.CONFIRM_CODE -> {
            "Confirm Code"
        }

        AuthMode.CHANGE_PASSWORD -> {
            "Change Password"
        }

        else -> {
            "Forgot Password"
        }
    }

//    Update status bar icon colors
    DisposableEffect(activity) {
        activity?.enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )

        onDispose {
            activity?.enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    android.graphics.Color.TRANSPARENT,
                    android.graphics.Color.TRANSPARENT
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 48.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            modifier = Modifier.fillMaxSize(0.85f),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            //  Title text and Back arrow
            TextButton(
                onClick = onBackClick,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Black
                ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Arrow"
                    )
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 28.sp,
                    )
                }
            }

            // Content card
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Card(

                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp,
                    )
                ) {
                    ForgotPasswordForm(title, mode,
                        onButtonClick = {
                        viewModel.handleAuth(onSuccess = {
                            onHandleForgotPassword()
                        }
                        )
                    })
                }
            }
        }
    }
}

