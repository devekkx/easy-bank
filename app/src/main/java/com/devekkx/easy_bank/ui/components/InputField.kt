package com.devekkx.easy_bank.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

enum class InputType {
    TEXT, PASSWORD, EMAIL
}

@Composable
fun InputField(
    type: InputType,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(20.dp),
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Next,
    onAction: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val isError = errorMessage != null
    val focusManager = LocalFocusManager.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val visualTransformation = if (type == InputType.PASSWORD && !passwordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    val keyboardType = when (type) {
        InputType.EMAIL -> KeyboardType.Email
        InputType.PASSWORD -> KeyboardType.Password
        else -> KeyboardType.Text
    }


    val keyboardActions = KeyboardActions(
        onNext = {
            focusManager.moveFocus(FocusDirection.Down)
            onAction()
        },
        onDone = {
            focusManager.clearFocus()
            onAction()
        }
    )

    Column {
        OutlinedTextField(
            value,
            onValueChange,
            singleLine = true,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            shape = shape,
            isError = isError,
            keyboardActions = keyboardActions,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
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

        // Show error text if it exists
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}