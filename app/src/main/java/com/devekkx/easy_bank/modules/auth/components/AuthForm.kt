package com.devekkx.easy_bank.modules.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.devekkx.easy_bank.modules.auth.AuthViewModel
import com.devekkx.easy_bank.ui.components.InputField
import com.devekkx.easy_bank.ui.components.InputType
import com.devekkx.easy_bank.ui.theme.SecondaryLight
import com.devekkx.easy_bank.ui.theme.White

@Composable
fun AuthForm(
    isRegister: Boolean = false,
    viewModel: AuthViewModel = hiltViewModel(), modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        if (isRegister) {
            InputField(
                type = InputType.TEXT,
                label = "Name",
                value = viewModel.name,
                onValueChange = { viewModel.onNameChange(it) },
                errorMessage = viewModel.nameError,
            )
        }
        InputField(
            type = InputType.EMAIL,
            label = "Email",
            value = viewModel.email,
            onValueChange = { viewModel.onEmailChange(it) },
            errorMessage = viewModel.emailError,
        )

        InputField(
            type = InputType.PASSWORD,
            label = "Password",
            value = viewModel.password,
            imeAction = ImeAction.Done,
            onValueChange = { viewModel.onPasswordChange(it) },
            onAction = {
                viewModel.login()
            }
        )

        Button(
            onClick = { viewModel.login() },
            enabled = viewModel.isLoginFormValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = White,
                disabledContainerColor = SecondaryLight
            )
        ) {
            Text(
                text = if (isRegister) " Sign Up" else "Sign In",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
