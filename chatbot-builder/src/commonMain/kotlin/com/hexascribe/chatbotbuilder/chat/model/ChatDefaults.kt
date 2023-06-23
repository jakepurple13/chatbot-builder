package com.hexascribe.chatbotbuilder.chat.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import co.yml.ychat.domain.model.ChatMessage
import com.hexascribe.chatbotbuilder.base.RoleEnum
import com.hexascribe.chatbotbuilder.chat.theme.icons.IconBot

public data class ChatDefaults(
    val errorText: String = "Not Delivered. Tap to try again.",
    val loadingText: String = "Typing",
    val inputFieldHint: String = "Send a message",
    val botIconBitmap: ImageBitmap? = null,
    val maxTokens: Int = 1024,
    val preMadeMessages: ChatDefaultScope.() -> Unit = {},
) {
    val preSeededMessages: MutableList<ChatMessage> = mutableListOf()
    val messages: MutableList<ChatMessage> = mutableListOf()

    init {
        object : ChatDefaultScope {
            override fun addPreSeededMessage(role: RoleEnum, content: String) {
                preSeededMessages.add(ChatMessage(role.name.lowercase(), content))
            }

            override fun addMessage(role: RoleEnum, content: String) {
                messages.add(ChatMessage(role.name.lowercase(), content))
            }
        }.preMadeMessages()
    }

    @Composable
    internal fun BotIcon() {
        if (botIconBitmap != null) {
            Image(botIconBitmap, null)
        } else {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(0xFF19C37D))
                    .padding(4.dp),
                tint = Color.White,
                imageVector = Icons.IconBot(),
                contentDescription = null
            )
        }
    }

    public companion object {
        public fun Default(preMadeMessages: ChatDefaultScope.() -> Unit = {}): ChatDefaults =
            ChatDefaults(preMadeMessages = preMadeMessages)
    }
}

public interface ChatDefaultScope {
    public fun addPreSeededMessage(role: RoleEnum, content: String)

    public fun addMessage(role: RoleEnum, content: String)
}
