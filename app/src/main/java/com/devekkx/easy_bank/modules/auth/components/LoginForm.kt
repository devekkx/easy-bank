package com.devekkx.easy_bank.modules.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devekkx.easy_bank.modules.auth.AuthViewModel
import com.devekkx.easy_bank.ui.components.InputField
import com.devekkx.easy_bank.ui.components.InputType

@Preview
@Composable
fun LoginForm(viewModel: AuthViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InputField(
            type = InputType.EMAIL,
            placeholder = "Email",
            value = viewModel.email,
            onValueChange = { viewModel.onEmailChange(it) },
            errorMessage = viewModel.emailError
        )

        InputField(
            type = InputType.PASSWORD,
            placeholder = "Password",
            value = viewModel.password,
            onValueChange = { viewModel.onPasswordChange(it) }
        )

        Button(
            onClick = { viewModel.login() },
            enabled = viewModel.isFormValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Sign In",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
