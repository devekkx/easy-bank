package com.devekkx.easy_bank.modules.auth.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.devekkx.easy_bank.modules.auth.AuthViewModel
import com.devekkx.easy_bank.ui.components.InputField
import com.devekkx.easy_bank.ui.components.InputType
import com.devekkx.easy_bank.ui.components.PasswordRequirementChecker
import com.devekkx.easy_bank.ui.theme.SecondaryLight
import com.devekkx.easy_bank.ui.theme.White

@Composable
fun AuthForm(
    isRegister: Boolean = false,
    viewModel: AuthViewModel = hiltViewModel(), modifier: Modifier = Modifier
) {
    var isPasswordFocused by remember { mutableStateOf(false) }
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
            errorMessage = if (isRegister) viewModel.emailError else null
        )

        Column {
            InputField(
                type = InputType.PASSWORD,
                label = "Password",
                value = viewModel.password,
                imeAction = if (isRegister) ImeAction.Next else ImeAction.Done,
                onValueChange = { viewModel.onPasswordChange(it) },
//                errorMessage = if (isRegister) viewModel.passwordError else null,
                isError = if (isRegister) viewModel.password !== "" && !viewModel.isPasswordValid
                else false,
                modifier = Modifier.onFocusChanged { isPasswordFocused = it.isFocused },
                onAction = {
                    viewModel.handleAuth(onSuccess = {})
                }

            )
//        Password validity indicators
            if (isRegister) {
                AnimatedVisibility(visible = isPasswordFocused || viewModel.password.isNotEmpty()) {
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        PasswordRequirementChecker("At least 8 characters", viewModel.hasMinLength)
                        PasswordRequirementChecker(
                            "Contains an uppercase letter",
                            viewModel.hasUpperCase
                        )
                        PasswordRequirementChecker(
                            "Contains a lowercase letter",
                            viewModel.hasLowerCase
                        )
                        PasswordRequirementChecker("Contains a number", viewModel.hasDigit)
                        PasswordRequirementChecker(
                            "Contains a special character",
                            viewModel.hasSpecialCharacter
                        )
                    }
                }
            }
        }

        if (isRegister) {
            InputField(
                type = InputType.PASSWORD,
                label = "Confirm Password",
                value = viewModel.confirmPassword,
                imeAction = ImeAction.Done,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                errorMessage = viewModel.confirmPasswordError,
                onAction = {
                    viewModel.handleAuth(onSuccess = {})
                }
            )

        }

        Button(
            onClick = { viewModel.handleAuth(onSuccess = {}) },
            enabled = if (isRegister) viewModel.isSignUpFormValid else viewModel.isLoginFormValid,
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
