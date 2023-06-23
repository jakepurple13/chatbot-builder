package com.hexascribe.chatbotbuilder.chat.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.dp
import com.hexascribe.chatbotbuilder.chat.model.ChatDefaults
import com.hexascribe.chatbotbuilder.chat.theme.color.LocalChatBotColors

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun BalloonUserMessage(
    text: String,
    chatDefaults: ChatDefaults = ChatDefaults(),
    isError: Boolean = false,
    onTryAgain: (String) -> Unit = {}
) {
    var showError by remember { mutableStateOf(false) }
    showError = isError
    Column(
        Modifier
            .fillMaxWidth()
            .clickable(isError) { if (isError) onTryAgain(text) },
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val chatBotColors = LocalChatBotColors.current
            Card(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                ),
                colors = CardDefaults.cardColors(
                    containerColor = chatBotColors.userBalloonColor.takeOrElse { MaterialTheme.colorScheme.surfaceVariant },
                    contentColor = chatBotColors.userBalloonTextColor.takeOrElse { MaterialTheme.colorScheme.onSurfaceVariant }
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 8.dp),
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            AnimatedVisibility(visible = showError, enter = scaleIn(), exit = scaleOut()) {
                Icon(
                    Icons.Outlined.Warning,
                    null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
        AnimatedVisibility(visible = showError, enter = scaleIn(), exit = scaleOut()) {
            Text(
                chatDefaults.errorText,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
internal fun BalloonBotMessage(
    text: String,
    chatDefaults: ChatDefaults = ChatDefaults()
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(end = 64.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            chatDefaults.BotIcon()
            val chatBotColors = LocalChatBotColors.current
            ElevatedCard(
                shape = RoundedCornerShape(
                    bottomStart = 16.dp,
                    topEnd = 16.dp,
                    bottomEnd = 16.dp,
                ),
                elevation = CardDefaults.elevatedCardElevation(4.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = chatBotColors.botBalloonColor.takeOrElse { MaterialTheme.colorScheme.surface },
                    contentColor = chatBotColors.botBalloonTextColor.takeOrElse { MaterialTheme.colorScheme.onSurface }
                )
            ) {
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
internal fun BalloonBotTyping(
    chatDefaults: ChatDefaults = ChatDefaults()
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(end = 64.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            chatDefaults.BotIcon()
            Card(
                shape = RoundedCornerShape(
                    bottomStart = 16.dp,
                    topEnd = 16.dp,
                    bottomEnd = 16.dp,
                )
            ) {
                TypingTextLoading(
                    chatDefaults = chatDefaults,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 8.dp),
                )
            }
        }
    }
}
