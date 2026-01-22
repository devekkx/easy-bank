package com.devekkx.easy_bank.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devekkx.easy_bank.R
import com.devekkx.easy_bank.modules.auth.components.LoginForm
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
            contentAlignment = Alignment.BottomEnd
        ) {

            // Back arrow and sign in text
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
//                    contentDescription = "Back",
//                    tint = Color.White,
//                    modifier = Modifier.size(32.dp)
//                )
//                Spacer(Modifier.width(8.dp))
//                Text(
//                    text = "Sign in",
//                    color = Color.White,
//                    style = MaterialTheme.typography.headlineSmall,
//                    fontWeight = FontWeight.Bold
//                )
//            }
        }

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
            Column {
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Primary,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Hello there, sign in to continue",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }

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

            //            // Input Fields
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                placeholder = { Text("Email input") },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp)
//            )
//
//            Spacer(Modifier.height(16.dp))
//
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                placeholder = { Text("Password") },
//                trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, null) },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                visualTransformation = PasswordVisualTransformation()
//            )
//
//            TextButton(
//                onClick = onForgotClick,
//                modifier = Modifier.align(Alignment.End)
//            ) {
//                Text("Forgot your password ?", color = Color.Gray)
//            }
//
//            Spacer(Modifier.height(24.dp))
//
//            // --- Sign In Button ---
//            Button(
//                onClick = onLoginSuccess,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(56.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F3F9)),
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                Text("Sign in", color = Color.LightGray) // Matches disabled look in image
//            }
//
//            Spacer(Modifier.height(24.dp))
//
//            // --- Fingerprint Icon ---
//            Icon(
//                imageVector = Icons.Default.Fingerprint,
//                contentDescription = "Biometric Login",
//                modifier = Modifier
//                    .size(64.dp)
//                    .clickable { /* Biometric Logic */ },
//                tint = Color(0xFF3F37C9)
//            )
//
//            Spacer(Modifier.height(24.dp))
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