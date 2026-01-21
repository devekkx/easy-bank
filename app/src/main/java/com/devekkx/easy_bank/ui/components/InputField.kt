package com.devekkx.easy_bank.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

enum class InputType {
    TEXT, PASSWORD, EMAIL
}

@Composable
fun InputField(
    type: InputType,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    shape: RoundedCornerShape,
    modifier: Modifier = Modifier
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    when (type) {
        InputType.EMAIL -> KeyboardOptions(keyboardType = KeyboardType.Email)
        InputType.PASSWORD -> KeyboardOptions(keyboardType = KeyboardType.Password)
        else -> KeyboardOptions(keyboardType = KeyboardType.Text)
    }

    val visualTransformation = if (type == InputType.PASSWORD && !passwordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    OutlinedTextField(
        value,
        onValueChange,
        placeholder = { Text(placeholder) },
        modifier = modifier,
        shape = shape,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (type == InputType.PASSWORD) {
                val icon = if (passwordVisible)
                    Icons.Filled.VisibilityOff
                else
                    Icons.Filled.Visibility
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = description)
                }
            }
        }
    )
}