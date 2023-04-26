//
//  CategoriesViewModel.swift
//  iosApp
//
//  Created by Luu Phan on 26/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared


enum CategoriesAction {
    case getList
}

class CategoriesViewModel: ObservableObject {
    
    @Published var tuples: [ItemCategoriesMovie]
    
    @Published var selectItem: ItemCategoriesMovie
    
    let shareMovie = ShareMovie()
    
    init() {
        self.tuples = CategoriesMovie.allCases.map { ItemCategoriesMovie(type: $0, movies: [],isLoading: true) }
        self.selectItem = ItemCategoriesMovie(type: .nowPlaying, movies: [], isLoading: true)
        goTab(.nowPlaying)
    }
    
    public func goTab(_ tab: CategoriesMovie) {
        let item =  tuples.first { $0.type == tab }
        selectItem = item!
        updateMovies(tab)
    }
    
    func dispatch(action: CategoriesAction) {
        switch action {
        case .getList:
            return
        }
    }
    
    func updateMovies(_ tab: CategoriesMovie) {
        Task { @MainActor in
            let findItem = tuples.firstIndex { $0.type == tab }
            if(tuples[findItem!].movies.isEmpty) {
                let newList = await getListMovie(tab)
                tuples[findItem!] = tuples[findItem!].copyWith(movies: newList, isLoading: false)
            }
            selectItem = tuples[findItem!]
        }
    }
    
    func getListMovie(_ tab: CategoriesMovie) async -> [Movie] {
        do {
            let data = try await shareMovie.getMovies(category: tab.getValue(), page: 1)
            return data
        } catch {
            return []
        }
    }
}

struct ItemCategoriesMovie {
    public var type: CategoriesMovie
    
    public var movies: [Movie]
    
    public var isLoading: Bool
    
    public init(type: CategoriesMovie, movies: [Movie], isLoading: Bool) {
        self.type = type
        self.movies = movies
        self.isLoading = isLoading
    }
    
    public func copyWith(type: CategoriesMovie? = nil, movies: [Movie]? = nil, isLoading: Bool?) -> ItemCategoriesMovie {
        ItemCategoriesMovie(type: type ?? self.type, movies: movies ?? self.movies, isLoading: isLoading ?? self.isLoading)
    }
}
