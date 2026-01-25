package com.devekkx.easy_bank.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devekkx.easy_bank.R
import com.devekkx.easy_bank.modules.auth.components.AuthForm
import com.devekkx.easy_bank.modules.auth.components.HeaderTexts
import com.devekkx.easy_bank.navigation.AuthMode
import com.devekkx.easy_bank.ui.components.AnnotatedStringWithClick
import com.devekkx.easy_bank.ui.theme.Primary

@Composable
fun AuthScreen(
    mode: AuthMode = AuthMode.LOGIN,
    onAuthActionClick: () -> Unit,
    onForgotClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isRegister = mode == AuthMode.REGISTER
    print(isRegister)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // Blue Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Primary)
                .padding(start = 16.dp, bottom = 64.dp),
        )

        // White Content Body with Curved Top
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-50).dp)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Heading texts
            HeaderTexts(
                title = if (isRegister) "Welcome to us" else "Welcome Back",
                subtitle = if (isRegister) "Hello there, create New account" else "Hello there, sign in to continue",
            )
            Spacer(Modifier.height(32.dp))

            // Login Illustration
            Box(
                contentAlignment = Alignment.Center,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            ) {

                if (isRegister) {
                    Image(
                        painter = painterResource(id = R.drawable.signup_illustration),
                        contentDescription = "Sign up Illustration",
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.login_illustration),
                        contentDescription = "Login Illustration",
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            // Signup and Login (Authentication) form composable
            AuthForm(isRegister)

            if (!isRegister) {
                TextButton(
                    onClick = onForgotClick,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Forgot your password ?", color = Color.Gray)
                }

                Spacer(Modifier.height(24.dp))

                // Fingerprint Icon for sign in screen
                Icon(
                    imageVector = Icons.Default.Fingerprint,
                    contentDescription = "Biometric Login",
                    modifier = Modifier
                        .size(64.dp)
                        .clickable { /* Biometric Logic */ },
                    tint = Color(0xFF3F37C9)
                )
            }
            Spacer(Modifier.height(24.dp))

//            Sign up and login link
            AnnotatedStringWithClick(
                label = if (isRegister) "Have an account?" else "Don't have an account?",
                linkText = if (isRegister) "Sign in" else "Sign up",
                onClick = onAuthActionClick,
            )
        }
    }

}