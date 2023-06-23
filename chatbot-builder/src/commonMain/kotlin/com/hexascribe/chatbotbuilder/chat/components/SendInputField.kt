package com.hexascribe.chatbotbuilder.chat.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SendInputField(
    value: String,
    modifier: Modifier = Modifier,
    hint: String = "",
    isButtonVisible: Boolean = true,
    readOnly: Boolean = false,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onButtonClick: () -> Unit = {},
    onTextChanged: (String) -> Unit = {},
) {
    var hasFocus by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = { onTextChanged(it) },
        modifier = modifier
            .onFocusChanged { focusState -> hasFocus = focusState.hasFocus }
            .fillMaxWidth(),
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onSend = { onButtonClick() }),
        maxLines = maxLines,
        placeholder = { Text(hint) },
        visualTransformation = visualTransformation,
        trailingIcon = {
            AnimatedVisibility(
                isButtonVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(
                    onClick = onButtonClick,
                    enabled = isButtonVisible,
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = null
                    )
                }
            }
        }
    )
}
