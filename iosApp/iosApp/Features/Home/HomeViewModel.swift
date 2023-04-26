//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Luu Phan on 25/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

public enum HomeTab: CaseIterable {
    case home
    case search
    case download
    case profile
}

public enum HomeAction {
    case goTab
}

public class HomeViewModel: ObservableObject {
    
    @Published var homeTab: HomeTab
    
    public init() {
        homeTab = .home
    }
    
    public func dispatch(action: HomeAction) {
        
    }
    public func goTab(tab: HomeTab) {
        homeTab = tab
    }
}
