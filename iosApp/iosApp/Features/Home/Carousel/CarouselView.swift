//
//  CarouselView.swift
//  iosApp
//
//  Created by Luu Phan on 25/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import NukeUI

struct CarouselView: View {
    
    @ObservedObject var viewModel:CarouselViewModel
    
    var theme: AppTheme = AppTheme()
    
    var body: some View {
        
        VStack {
            if !viewModel.listImageMovie.isEmpty {
                ACarousel(viewModel.listImageMovie,
                          headspace: UIScreen.screenWidth / 9,
                          sidesScaling: 0.9,
                          autoScroll: .active(3)
                          
                ) { item in
                    LazyImage(source: ImageRequest(url: URL(string: "https://image.tmdb.org/t/p/original\(item.backdropPath ?? "")"))) { state in
                        if let image = state.image {
                            if #available(iOS 15.0, *) {
                                image
                                    .frame(width: 295, height: 154)
                                    .cornerRadius(16)
                                    .overlay(alignment: .bottomLeading) {
                                        VStack(alignment: .leading) {
                                            Text(item.title ?? "--")
                                                .font(.system(size: 17))
                                                .fontWeight(.bold)
                                                .foregroundColor(theme.whiteShades)
                                            Text(item.releaseDate ?? "--")
                                                .font(.system(size: 12))
                                                .fontWeight(.bold)
                                                .foregroundColor(theme.grey92929D)
                                                .multilineTextAlignment(.leading)
                                                .padding(.leading, 8)
                                        }
                                        .padding(.leading, 4)
                                        
                                    }
                            } else {
                                Image(systemName: "x.circle")
                            }
                        } else if state.error != nil {
                            Image(systemName: "x.circle")
                                .frame(width: 295, height: 154)
                                .background(theme.blue252836)
                                .cornerRadius(16)
                        } else {
                            VStack {
                                ProgressView()
                                    .progressViewStyle(CircularProgressViewStyle(tint: theme.whiteShades))
                            }
                            .frame(width: 295, height: 154)
                            .background(theme.blue252836)
                            .cornerRadius(16)
                        }
                    }
                }
            }
        }
        .frame(height: 180)
        .onAppear{
            viewModel.dispatch(action: .getlist)
        }
    }
}
