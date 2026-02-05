package com.devekkx.easy_bank.modules.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.devekkx.easy_bank.modules.auth.AuthViewModel
import com.devekkx.easy_bank.navigation.AuthMode
import com.devekkx.easy_bank.ui.components.InputField
import com.devekkx.easy_bank.ui.components.InputType
import com.devekkx.easy_bank.ui.theme.Primary

@Composable
fun ForgotPasswordForm(
    title: String,
    mode: AuthMode,
    onButtonClick: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(), modifier: Modifier = Modifier

) {

    val isFormValid = when (mode) {
        AuthMode.CONFIRM_CODE -> viewModel.isConfirmationCodeValid
        else -> viewModel.isForgotPasswordFormValid
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (mode) {
            AuthMode.CHANGE_PASSWORD -> {
                InputField(
                    modifier = Modifier.fillMaxWidth(),
                    type = InputType.PASSWORD,
                    label = "Type your new password",
                    value = viewModel.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                )
                InputField(
                    modifier = Modifier.fillMaxWidth(),
                    type = InputType.PASSWORD,
                    label = "Confirm password",
                    value = viewModel.confirmPassword,
                    imeAction = ImeAction.Done,
                    onValueChange = { viewModel.onConfirmPasswordChange(it) },
                )
            }

            AuthMode.CONFIRM_CODE -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    InputField(
                        modifier = Modifier.weight(1.5f),
                        type = InputType.PHONE,
                        label = "Type code",
                        value = viewModel.confirmationCode,
                        imeAction = ImeAction.Done,
                        onValueChange = { viewModel.onConfirmationCodeChange(it) }
                    )
                    Button(
                        modifier = Modifier
                            .fillMaxHeight(),
                        enabled = isFormValid,
                        onClick = {},
                    ) {
                        Text(text = "Resend Code")
                    }
                }
                Text(
                    text = buildAnnotatedString {
                        append("We sent you a code to verify your phone number ")

                        // Start colored section
                        withStyle(
                            style = SpanStyle(
                                color = Primary,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(viewModel.phone)
                        }
                    },
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "This code will expired 10 minutes after this message. If you don't get a message.",
                    fontWeight = FontWeight.Medium,
                )
            }

            else -> {
                InputField(
                    type = InputType.PHONE,
                    label = "Type your phone number",
                    value = viewModel.phone,
                    onValueChange = { viewModel.onPhoneChange(it) },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Enter your phone number to get verification code",
                    fontWeight = FontWeight.Medium,
                    maxLines = 2
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            enabled = isFormValid,
            onClick = { onButtonClick() }
        ) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                color = Color.White,
                text = if (mode == AuthMode.FORGOT_PASSWORD) "Send Code" else title
            )
        }
    }
}