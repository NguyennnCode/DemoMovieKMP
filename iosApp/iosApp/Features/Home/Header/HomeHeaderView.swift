//
//  HomeHeaderView.swift
//  iosApp
//
//  Created by Luu Phan on 25/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import NukeUI

struct HomeHeaderView: View {
    
    var theme: AppTheme = AppTheme()
    
    var body: some View {
        HStack {
            LazyImage(source: ImageRequest(url: URL(string: "https://lh3.googleusercontent.com/a/AGNmyxbOXURs9BTEMgLz5DY8CJONvRggbX1D9YZ82FDP=s288"))) { state in
                
                if let image = state.image {
                    image
                        .frame(width: 50, height: 50)
                        .foregroundColor(.red)
                        .clipShape(Circle())
                        .padding(.leading, 8)
                    
                } else if state.error != nil {
                    Image(systemName: "x.circle")
                        .frame(width: 50, height: 50)
                        .foregroundColor(.red)
                        .clipShape(Circle())
                        .padding(.leading, 8)
                } else {
                    VStack {
                        ProgressView()
                            .progressViewStyle(CircularProgressViewStyle(tint: theme.whiteShades))
                    }
                    .frame(width: 50, height: 50)
                    .foregroundColor(.red)
                    .clipShape(Circle())
                    .padding(.leading, 8)
                }
            }
            
            VStack(alignment: .leading, spacing: 3) {
                Text("Hello, Smith")
                    .foregroundColor(theme.whiteShades)
                    .font(.title)
                    .fontWeight(.bold)
                Text("Let’s stream your favorite movie")
                    .foregroundColor(theme.grey)
                    .font(.caption)
                    .fontWeight(.bold)
                    .multilineTextAlignment(.leading)
            }
            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
            .padding(.leading, 4)
            
            Image(systemName: "heart.fill")
            
                .frame(width: 40, height: 40)
                .foregroundColor(.red)
                .background(theme.blue242634)
                .cornerRadius(15)
                .padding(.trailing, 8)
            
        }
        .frame(maxWidth: .infinity)
        .frame(height: 60)
        .padding(.horizontal, 16)
        .background(theme.primary)
        .cornerRadius(15)
    }
}

struct HomeHeaderView_Previews: PreviewProvider {
    static var previews: some View {
        HomeHeaderView()
    }
}

