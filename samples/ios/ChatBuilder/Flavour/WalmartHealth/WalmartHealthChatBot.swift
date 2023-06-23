//
//  WalmartHealthChatKit.swift
//  iosApp
//
//  Created by Koji Osugi on 14/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import ChatBotBuilder
import UIKit
import SwiftUI

class WalmartHealthChatBot: ChatBotFactory {
    
    private let initialBotMessage = "Hello, here the GPT assistant from Walmart Health, how can I assist you today?"
    
    private let preSeededMessages = "You are a helpful chatbot from https://www.sutterhealth.org/ website. You will answer only questions related to healthcare such as workout routines, diet plans, health advice, etc.\nAnswer all questions in a super short and objective way"
    
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
        "logo_walmart"
    }
    
    func toolbarColor() -> Color {
        .walmartHealth
    }
}
