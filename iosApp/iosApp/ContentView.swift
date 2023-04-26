import SwiftUI
import shared

struct ContentView: View {

    var theme: AppTheme = AppTheme()
    var homeViewModel: HomeViewModel = HomeViewModel()
    
    
	var body: some View {

        HomeView(viewModel: homeViewModel)
        .background(theme.primary)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
