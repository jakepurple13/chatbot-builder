package com.hexascribe.chatbotbuilder.chat.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import co.yml.ychat.YChat
import co.yml.ychat.entrypoint.features.ChatCompletions
import com.hexascribe.chatbotbuilder.base.RoleEnum
import com.hexascribe.chatbotbuilder.chat.model.ChatDefaults
import com.hexascribe.chatbotbuilder.chat.model.MessageType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
internal class ChatState(
    private val chatDefaults: ChatDefaults,
    private val yChat: YChat,
    private val coroutineScope: CoroutineScope
) {
    var message by mutableStateOf("")

    val messages = mutableStateListOf(
        *chatDefaults.messages.mapNotNull {
            if (RoleEnum.isAssistant(it.role)) MessageType.Bot(it.content)
            else if (RoleEnum.isUser(it.role)) MessageType.User(it.content)
            else null
        }.toTypedArray()
    )

    val onButtonVisible by derivedStateOf { message.isNotEmpty() && MessageType.Loading !in messages }

    private val chatCompletions: ChatCompletions

    init {
        chatCompletions = createChatCompletions()
    }

    fun onMessage(message: String) {
        this.message = message
    }

    fun onTryAgain(message: String) {
        onError(false)
        this.message = message
        sendMessage()
    }

    fun sendMessage() = coroutineScope.launch {
        val messageToSend = message
        messages.add(MessageType.User(message))
        onLoading(true)
        onMessage("")
        runCatching { chatCompletions.execute(messageToSend) }
            .also { onLoading(false) }
            .onSuccess { messages.add(MessageType.Bot(it.first().content)) }
            .onFailure { onError(true) }
    }

    private fun onLoading(isLoading: Boolean) {
        if (isLoading) {
            onError(false)
            messages.add(MessageType.Loading)
        } else {
            messages.remove(MessageType.Loading)
        }
    }

    private fun onError(isError: Boolean) {
        if (isError) {
            val error = messages.removeLast() as? MessageType.User ?: return
            error.isError = true
            messages.add(error)
        } else {
            messages.removeAll { it is MessageType.User && it.isError }
        }
    }

    private fun createChatCompletions(): ChatCompletions {
        val chatCompletions = yChat
            .chatCompletions()
            .setMaxTokens(chatDefaults.maxTokens)
        chatDefaults.preSeededMessages.forEach {
            chatCompletions.addMessage(it.role, it.content)
        }
        return chatCompletions
    }
}
