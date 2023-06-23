//
//  HealthChatBot.swift
//  ios
//
//  Created by Koji Osugi on 19/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import ChatBotBuilder
import UIKit
import SwiftUI

class HealthChatBot: ChatBotFactory {
    
    private let initialBotMessage = "Hi, I'm Health GPT, here to assist you. Feel free to ask me anything related to health, diet, and other related topics. I'll do my best to help you."
    
    private let preSeededMessages = "You are a helpful health assistant, capable of answering any questions related to health, diet, and other related topics."
    
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
    
    func toolbarTitle() -> String? {
        "Health Chat"
    }
    
    func toolbarColor() -> Color {
        .white
    }
    
    func toolbarBackButtonColor() -> Color {
        .black
    }
}
