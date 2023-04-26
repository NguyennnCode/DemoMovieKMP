//
//  CategoriesEnum.swift
//  iosApp
//
//  Created by Luu Phan on 26/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

enum CategoriesMovie: String, CaseIterable, Equatable {
    case nowPlaying
    case topRate
    case upComing
    case upLatest
    case popular
}

extension CategoriesMovie {
    func showDisplay() -> String {
        switch self {
        case .nowPlaying:
            return "Now Playing"
        case .topRate:
            return "Top Rate"
        case .upComing:
            return "Coming"
        case .upLatest:
            return "Latest"
        case .popular:
            return "Popular"
        }
    }
    func getValue() -> String {
        switch self {
        case .nowPlaying:
            return "now_playing"
        case .topRate:
            return "top_rated"
        case .upComing:
            return "upcoming"
        case .upLatest:
            return "latest"
        case .popular:
            return "popular"
        }
    }
}
