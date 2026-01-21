package com.devekkx.easy_bank.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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
        // --- Blue Header Section ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFF3F37C9))
                .padding(top = 48.dp, start = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Sign in",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // --- White Content Body with Curved Top ---
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .offset(y = (-40).dp) // Pull up to overlap blue background
//                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
//                .background(Color.White)
//                .padding(24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Welcome Back",
//                style = MaterialTheme.typography.headlineMedium,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.fillMaxWidth()
//            )
//            Text(
//                text = "Hello there, sign in to continue",
//                color = Color.Gray,
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(Modifier.height(32.dp))
//
//            // --- Lock Illustration (Simplified with Circles) ---
//            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(150.dp)) {
//                // Background circle
//                Box(
//                    modifier = Modifier
//                        .size(100.dp)
//                        .clip(CircleShape)
//                        .background(Color(0xFFEDEDFF))
//                )
//                // Lock Icon
//                Icon(
//                    imageVector = Icons.Default.Lock,
//                    contentDescription = null,
//                    modifier = Modifier.size(48.dp),
//                    tint = Color(0xFF3F37C9)
//                )
//                // Decorative colorful dots (Example of placement)
//                Canvas(modifier = Modifier.fillMaxSize()) {
//                    drawCircle(Color(0xFFFF4D6D), radius = 8f, center = Offset(x = 280f, y = 50f))
//                    drawCircle(Color(0xFF4CC9F0), radius = 12f, center = Offset(x = 50f, y = 150f))
//                    drawCircle(Color(0xFFF7B731), radius = 10f, center = Offset(x = 80f, y = 300f))
//                }
//            }
//
//            Spacer(Modifier.height(32.dp))
//
//            // --- Input Fields ---
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
//        }
    }

}