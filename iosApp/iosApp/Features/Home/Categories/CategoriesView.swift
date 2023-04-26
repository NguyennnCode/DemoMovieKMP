//
//  CategoriesView.swift
//  iosApp
//
//  Created by Luu Phan on 26/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CategoriesView: View {
    
    @ObservedObject var viewModel:CategoriesViewModel
    
    var theme: AppTheme = AppTheme()
    
    public init(viewModel: CategoriesViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 15) {
            Text("Categories")
                .font(.system(size: 16))
                .fontWeight(.bold)
                .foregroundColor(theme.grey92929D)
                .lineLimit(1)
            
            ScrollView (.horizontal, showsIndicators: false) {
                HStack {
                    ForEach(viewModel.tuples.map{ $0.type }, id: \.rawValue) { tab in
                        Text(tab.showDisplay())
                            .font(.system(size: 12))
                            .fontWeight(.bold)
                            .foregroundColor(tab == viewModel.selectItem.type ? theme.blue12CDD9 : theme.whiteShades)
                            .frame(height: 31)
                            .padding(.horizontal, 8)
                            .padding(.vertical, 4)
                            .background(tab == viewModel.selectItem.type ? theme.blue171725 : theme.primary)
                            .cornerRadius(8)
                            .onTapGesture {
                                viewModel.goTab(tab)
                            }
                    }
                }
            }
            .padding(.bottom, 16)
            
            CategoriesViewItem(value: viewModel.selectItem)
            
        }
        .padding(.horizontal, 8)
        .background(theme.primary)
    }
}
