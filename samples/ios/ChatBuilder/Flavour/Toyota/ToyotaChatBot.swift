//
//  ToyotaChatKit.swift
//  iosApp
//
//  Created by Koji Osugi on 15/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import ChatBotBuilder

class ToyotaChatBot: ChatBotFactory {
    
    private let initialBotMessage = "Hello there! Let me know what your budget is, if you want to buy or lease, and any other details that can help me find the perfect vehicle for you."
    
    private let preSeededMessages = "You are a helpful Toyota salesman. Your main goal is to find the best suitable vehicle for the customer. Answer as concisely as possible."
    
    func factory() -> UIViewController {
        return ChatBotKt.ChatScreenViewController(
            apiKey: Config.apiKey,
            chatBotColors: ChatBotColors.companion.Default,
            chatDefaults: ChatDefaults.companion.Default(
                preMadeMessages: { s in
                    s.addMessage(role: .assistant, content: self.initialBotMessage)
                    s.addPreSeededMessage(role: .system, content: self.preSeededMessages)
                }
            )
        )
    }
    
    func logo() -> String? {
        "logo_toyota"
    }
    
    func toolbarColor() -> Color {
        .white
    }
    
    func toolbarBackButtonColor() -> Color {
        .black
    }
}
