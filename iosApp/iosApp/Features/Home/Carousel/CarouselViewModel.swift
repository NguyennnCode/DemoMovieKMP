//
//  CarouselViewModel.swift
//  iosApp
//
//  Created by Luu Phan on 25/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

public enum CarouselAction {
    case getlist
}

public class CarouselViewModel: ObservableObject {
    
    let shareMovie = ShareMovie()
    
    private var cancellables: [AnyCancellable] = []
    
    @Published var listImageMovie: [Movie] = []
    
    public init() {}
    
    public func dispatch(action: CarouselAction) {
        switch action {
        case .getlist:
            getlistNowplaying()
        }
        
    }
    
    func getlistNowplaying() {
        Task { @MainActor in
            let moviesData = try await shareMovie.getMovies(category: "upcoming", page: 1)
            listImageMovie = moviesData
        }
    }
}
