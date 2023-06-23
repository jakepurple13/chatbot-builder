package com.hexascribe.chatbotbuilder.chat.theme.color

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Stable
public class ChatBotColors(
    userBalloonColor: Color = Color.Unspecified,
    userBalloonTextColor: Color = Color.Unspecified,
    botBalloonColor: Color = Color.Unspecified,
    botBalloonTextColor: Color = Color.Unspecified,
) {
    internal var userBalloonColor: Color by mutableStateOf(userBalloonColor)
    internal var userBalloonTextColor: Color by mutableStateOf(userBalloonTextColor)
    internal var botBalloonColor: Color by mutableStateOf(botBalloonColor)
    internal var botBalloonTextColor: Color by mutableStateOf(botBalloonTextColor)

    public companion object {
        public val Default: ChatBotColors = ChatBotColors()
    }
}

internal val LocalChatBotColors = compositionLocalOf<ChatBotColors> { error("Nothing here!") }