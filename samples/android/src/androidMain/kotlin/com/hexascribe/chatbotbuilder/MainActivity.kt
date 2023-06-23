package com.hexascribe.chatbotbuilder

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.hexascribe.chatbotbuilder.base.RoleEnum
import com.hexascribe.chatbotbuilder.chat.model.ChatDefaults
import com.hexascribe.chatbotbuilder.chat.screen.ChatScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
            ) {
                ChatScreen(
                    apiKey = "YOUR_OPEN_AI_KEY",
                    chatDefaults = ChatDefaults {
                        addMessage(RoleEnum.ASSISTANT, "Hi, how can I help you today?")
                        addPreSeededMessage(
                            RoleEnum.SYSTEM,
                            "You are a helpful seller car assistant"
                        )
                    }
                )
            }
        }
    }
}
