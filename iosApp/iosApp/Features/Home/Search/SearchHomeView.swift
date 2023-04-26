//
//  SearchHomeView.swift
//  iosApp
//
//  Created by Luu Phan on 25/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SearchHomeView: View {
    var theme: AppTheme = AppTheme()
    
    @State private var username: String = ""
    
    var body: some View {
        HStack {
            
            Image(systemName: "magnifyingglass")
                .resizable()
                .frame(width: 20, height: 20)
                .foregroundColor(theme.grey92929D)
                .padding(.leading, 24)
            
            TextField(
                "User name (email address)",
                text: $username
            )
            .placeholder(when: username.isEmpty) {
                Text("Type here...")
            }
            .foregroundColor(theme.grey92929D)
            .padding(.leading, 4)
            .frame(maxWidth: .infinity)
            Divider()
                .background(theme.grey92929D)
                .frame(height: 20)
                .padding(.trailing, 4)
            Image(systemName: "slider.horizontal.3")
                .resizable()
                .frame(width: 20, height: 20)
                .foregroundColor(theme.grey92929D)
                .padding(.trailing, 24)
            
        }
        .frame(maxWidth: .infinity)
        .frame(height: 60)
        .background(theme.blue252836)
        .cornerRadius(30)   
    }
}

struct SearchHomeView_Previews: PreviewProvider {
    static var previews: some View {
        SearchHomeView()
    }
}
