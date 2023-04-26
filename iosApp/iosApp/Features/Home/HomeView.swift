//
//  HomeView.swift
//  iosApp
//
//  Created by Luu Phan on 25/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct HomeView: View {
    
    var theme: AppTheme = AppTheme()
    
    @ObservedObject var viewModel:HomeViewModel
    
    var carouselViewModel: CarouselViewModel = CarouselViewModel()
    var categoriesViewModel: CategoriesViewModel = CategoriesViewModel()
    
    var body: some View {
        if #available(iOS 15.0, *) {
            TabView(selection: $viewModel.homeTab) {
                VStack(alignment: .leading) {
                    HomeHeaderView()
                    SearchHomeView()
                        .padding(.all, 16)
                    CarouselView(viewModel: carouselViewModel)
                    CategoriesView(viewModel: categoriesViewModel)
                    Spacer()
                }
                .tag(HomeTab.home)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(theme.primary)
                
                Text("Search")
                    .font(.system(size: 40, weight: .bold, design: .default))
                    .tag(HomeTab.search)
                
                Text("Download")
                    .font(.system(size: 40, weight: .bold, design: .default))
                    .tag(HomeTab.download)
                
                Text("Profile")
                    .font(.system(size: 40, weight: .bold, design: .default))
                    .tag(HomeTab.profile)
            }
            .foregroundColor(theme.primary)
            .overlay {
                VStack {
                    Spacer()
                    HStack(alignment: .center) {
                        
                        ForEach(HomeTab.allCases, id: \.self) { tab in
                            buildTab(tab)
                                .padding(.horizontal, 8)
                        }
                        
                        
                    }
                    .padding(.horizontal, 45)
                    .frame(height: 60)
                    .frame(maxWidth: .infinity,alignment: .bottom)
                    .background(theme.primary)
                }
                .animation(.easeInOut, value: viewModel.homeTab)
                
            }
        } else {
            Text("iOS <15.0")
                .font(.system(size: 40, weight: .bold, design: .default))
                .foregroundColor(Color.white)
        }
    }
    
    @ViewBuilder
    func buildTab(_ tab: HomeTab) -> some View {
        let info = infoTab(tab)
        HStack {
            Image(systemName: info.image)
                .frame(width: 24, height: 24)
            if tab == viewModel.homeTab {
                Text(info.text)
                    .font(.system(size: 12))
                    .lineLimit(1)
            }
        }
        .foregroundColor(tab == viewModel.homeTab ? theme.blue12CDD9 : theme.grey92929D)
        .padding(.horizontal, 10)
        .frame(height: 40)
        .background(tab == viewModel.homeTab ? theme.blue252836 : theme.primary)
        .cornerRadius(16)
        .onTapGesture {
            viewModel.goTab(tab: tab)
        }
    }
    
    func infoTab(_ tab: HomeTab) -> (image: String, text: String) {
        switch tab {
        case .home:
            return (image: "house.fill", text: "Home")
        case .search:
            return (image: "magnifyingglass", text: "Search")
        case .download:
            return (image: "arrow.down.to.line.compact", text: "Download")
        case .profile:
            return (image: "person.fill", text: "Profile")
        }
    }
    
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView(viewModel: HomeViewModel())
    }
}
