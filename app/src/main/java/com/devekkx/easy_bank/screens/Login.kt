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
import com.devekkx.easy_bank.modules.auth.components.HeaderTexts
import com.devekkx.easy_bank.modules.auth.components.LoginForm
import com.devekkx.easy_bank.ui.components.AnnotatedStringWithLink
import com.devekkx.easy_bank.ui.theme.Primary

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                title = "Welcome Back",
                subtitle = "Hello there, sign in to continue",
            )
            Spacer(Modifier.height(32.dp))

            // Login Illustration
            Box(
                contentAlignment = Alignment.Center,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.login_illustration),
                    contentDescription = "Login Illustration",
                )
            }

            Spacer(Modifier.height(32.dp))

            // Login form composable
            LoginForm()

            TextButton(
                onClick = onForgotClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Forgot your password ?", color = Color.Gray)
            }

            AnnotatedStringWithLink()

            Spacer(Modifier.height(24.dp))

            // Fingerprint Icon
            Icon(
                imageVector = Icons.Default.Fingerprint,
                contentDescription = "Biometric Login",
                modifier = Modifier
                    .size(64.dp)
                    .clickable { /* Biometric Logic */ },
                tint = Color(0xFF3F37C9)
            )
            Spacer(Modifier.height(24.dp))

//
//            // --- Footer ---
//            Row {
//                Text("Don't have an account? ", color = Color.Gray)
//                Text(
//                    text = "Sign Up",
//                    color = Color(0xFF3F37C9),
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.clickable { onRegisterClick() }
//                )
//            }
        }
    }

}