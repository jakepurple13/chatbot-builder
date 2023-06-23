package com.hexascribe.chatbotbuilder

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.window.ComposeUIViewController
import com.hexascribe.chatbotbuilder.chat.model.ChatDefaults
import com.hexascribe.chatbotbuilder.chat.screen.ChatScreen
import com.hexascribe.chatbotbuilder.chat.theme.color.ChatBotColors
import platform.UIKit.UIViewController

public fun ChatScreenViewController(
    apiKey: String,
    chatBotColors: ChatBotColors = ChatBotColors(),
    chatDefaults: ChatDefaults = ChatDefaults()
): UIViewController = ComposeUIViewController {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        ChatScreen(
            apiKey = apiKey,
            chatBotColors = chatBotColors,
            chatDefaults = chatDefaults
        )
    }
}