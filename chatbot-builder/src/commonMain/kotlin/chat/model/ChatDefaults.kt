package chat.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import chat.theme.DarkColors
import chat.theme.DefaultColors
import chat.theme.IconResource
import co.yml.ychat.domain.model.ChatMessage

internal data class ChatDefaults(
    var isDarkModeEnabled: Boolean = false,
    var darkColors: DarkColors = DarkColors(),
    var defaultColors: DefaultColors = DefaultColors(),
    var preSeededMessages: ArrayList<ChatMessage> = ArrayList(),
    var messages: ArrayList<ChatMessage> = ArrayList(),
    var errorText: String = "Not Delivered. Tap to try again.",
    var loadingText: String = "Typing",
    var inputFieldBorderWidth: Int = 1,
    var inputFieldCornerRadius: Int = 8,
    var inputFieldHint: String = "Send a message",
    var botIconBitmap: ImageBitmap? = null,
    var maxTokens: Int = 1024,
) {

    val colors by lazy {
        if (isDarkModeEnabled) darkColors else defaultColors
    }

    @Composable
    fun BotIcon() {
        if (botIconBitmap != null) {
            Image(botIconBitmap!!, null)
        } else {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(0xFF19C37D))
                    .padding(4.dp),
                tint = Color.White,
                painter = IconResource.BOT.painter(),
                contentDescription = null
            )
        }
    }
}
