//
//  CategoriesViewItem.swift
//  iosApp
//
//  Created by Luu Phan on 26/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import NukeUI

struct CategoriesViewItem: View {
    
    var theme: AppTheme = AppTheme()
    
    let value: ItemCategoriesMovie
    
    init(value: ItemCategoriesMovie) {
        self.value = value
    }
    
    var body: some View {
        VStack(alignment: value.isLoading ? .center : .leading, spacing: 15) {
            HStack {
                Text("Most popular")
                    .font(.system(size: 16))
                    .fontWeight(.bold)
                    .foregroundColor(theme.grey92929D)
                    .lineLimit(1)
                Spacer()
                Text("See All")
                    .font(.system(size: 16))
                    .fontWeight(.bold)
                    .padding(.trailing, 16)
                    .foregroundColor(theme.blue12CDD9)
                    .lineLimit(1)
            }
            
            if value.isLoading {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: theme.whiteShades))
            }
            else if value.movies.isEmpty {
                Text("No movie here!")
                    .font(.system(size: 16))
                    .foregroundColor(theme.whiteShades)
                    .lineLimit(1)
                    .frame(maxWidth: .infinity)
                    .multilineTextAlignment(.center)
            } else {
                ScrollView (.horizontal, showsIndicators: false) {
                    HStack {
                        ForEach(value.movies) { movie in
                            
                            LazyImage(source: ImageRequest(url: URL(string: "https://image.tmdb.org/t/p/original\(movie.backdropPath ?? "")"))) { state in
                                if let image = state.image {
                                    VStack(alignment: .leading) {
                                        if #available(iOS 15.0, *) {
                                            image
                                                .frame(width: 135, height: 178)
                                                .padding(.bottom, 4)
                                                .overlay(alignment: .topTrailing) {
                                                    HStack(alignment: .center) {
                                                        Image(systemName: "star.fill")
                                                            .foregroundColor(theme.orangeFF8700)
                                                            .padding(.all, 4)
                                                            .frame(width: 12, height: 12)
                                                        
                                                        Text(String(format: "%.1f", movie.voteAverage ?? 0.0))
                                                            .font(.system(size: 14))
                                                            .foregroundColor(theme.orangeFF8700)
                                                        
                                                    }
                                                    .padding(.horizontal, 8)
                                                    .padding(.vertical, 4)
                                                    .background(theme.grey252836.opacity(0.32))
                                                    .cornerRadius(8)
                                                    .padding(.all, 8)
                                                    
                                                }
                                        } else {
                                            // Fallback on earlier versions
                                        }
                                        Text(movie.title ?? "No Name")
                                            .font(.system(size: 14))
                                            .fontWeight(.bold)
                                            .foregroundColor(theme.grey92929D)
                                            .padding(.horizontal, 8)
                                            .lineLimit(1)
                                            .multilineTextAlignment(.leading)
                                        
                                        Text(movie.overview ?? "----")
                                            .font(.system(size: 10))
                                            .foregroundColor(theme.grey92929D)
                                            .padding(.horizontal, 8)
                                            .lineLimit(1)
                                            .multilineTextAlignment(.leading)
                                        Spacer()
                                        
                                    }
                                    .frame(width: 135, height: 231)
                                    .background(theme.blue252836)
                                    .cornerRadius(16)
                                } else if state.error != nil {
                                    Image(systemName: "x.circle")
                                } else {
                                    VStack {
                                        ProgressView()
                                            .progressViewStyle(CircularProgressViewStyle(tint: theme.whiteShades))
                                    }
                                    .frame(width: 135, height: 231)
                                    .background(theme.blue252836)
                                    .cornerRadius(16)
                                }
                            }
                        }
                    }
                }
            }
            Spacer()
        }
        .padding(.horizontal, 8)
        .background(theme.primary)
        .frame(height: 235)
    }
}
