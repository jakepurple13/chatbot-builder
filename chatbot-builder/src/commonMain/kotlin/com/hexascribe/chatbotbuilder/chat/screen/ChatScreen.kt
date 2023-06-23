package com.hexascribe.chatbotbuilder.chat.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.yml.ychat.YChat
import com.hexascribe.chatbotbuilder.chat.components.BalloonBotMessage
import com.hexascribe.chatbotbuilder.chat.components.BalloonBotTyping
import com.hexascribe.chatbotbuilder.chat.components.BalloonUserMessage
import com.hexascribe.chatbotbuilder.chat.components.SendInputField
import com.hexascribe.chatbotbuilder.chat.model.ChatDefaults
import com.hexascribe.chatbotbuilder.chat.model.MessageType
import com.hexascribe.chatbotbuilder.chat.state.ChatState
import com.hexascribe.chatbotbuilder.chat.theme.color.ChatBotColors
import com.hexascribe.chatbotbuilder.chat.theme.color.LocalChatBotColors

@Composable
public fun ChatScreen(
    apiKey: String,
    modifier: Modifier = Modifier,
    chatBotColors: ChatBotColors = ChatBotColors(),
    chatDefaults: ChatDefaults = ChatDefaults(),
) {
    CompositionLocalProvider(LocalChatBotColors provides remember(chatBotColors) { chatBotColors }) {
        ChatScreenImpl(
            apiKey = apiKey,
            modifier = modifier,
            chatDefaults = chatDefaults
        )
    }
}

@Composable
internal fun ChatScreenImpl(
    apiKey: String,
    modifier: Modifier = Modifier,
    chatDefaults: ChatDefaults = ChatDefaults(),
    chatState: ChatState = rememberChatState(apiKey, chatDefaults),
) {
    Surface(modifier) {
        Column(Modifier.fillMaxHeight()) {
            val scrollState = rememberLazyListState()
            val messages = chatState.messages
            LazyColumn(
                state = scrollState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = 8.dp),
            ) {
                item { Spacer(modifier = Modifier.padding(top = 16.dp)) }
                items(messages) { item ->
                    when (item) {
                        is MessageType.User ->
                            BalloonUserMessage(
                                item.text,
                                chatDefaults,
                                item.isError,
                                onTryAgain = { chatState.onTryAgain(it) }
                            )

                        is MessageType.Bot ->
                            BalloonBotMessage(item.text, chatDefaults)

                        is MessageType.Loading ->
                            BalloonBotTyping(chatDefaults)
                    }
                }
            }
            SenderMessageSection(chatState, chatDefaults)
        }
    }
}

@Composable
private fun SenderMessageSection(
    chatState: ChatState,
    chatDefaults: ChatDefaults
) {
    Column {
        Divider()
        SendInputField(
            value = chatState.message,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            isButtonVisible = chatState.onButtonVisible,
            hint = chatDefaults.inputFieldHint,
            onTextChanged = chatState::onMessage,
            onButtonClick = chatState::sendMessage
        )
    }
}

@Composable
private fun rememberChatState(
    apiKey: String,
    chatDefaults: ChatDefaults,
): ChatState {
    val coroutine = rememberCoroutineScope()
    return remember { ChatState(chatDefaults, YChat.create(apiKey), coroutine) }
}
