//
//  FitnessChatBot.swift
//  ios
//
//  Created by Koji Osugi on 19/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import ChatBotBuilder
import UIKit
import SwiftUI

class FitnessChatBot: ChatBotFactory {
    
    private let initialBotMessage = "Hello, I'm a fitness coach here to assist you with anything related to exercise, gym tips, and much more."
    
    private let preSeededMessages = "You are a helpful assistant fitness coach, ready to answer any questions related to fitness, including gym tips, exercises, and more."
    
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
        "Fitness Coach"
    }
    
    func toolbarColor() -> Color {
        Color(hex: 0x212121)
    }
}
