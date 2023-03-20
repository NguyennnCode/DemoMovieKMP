import SwiftUI
import shared

struct ContentView: View {
    @State var text: String? = ""

	var body: some View {
		Text(text ?? "null")
		.onAppear {
		Task {
            let moviesData = try await ShareMovie().getMovies(category: "upcoming", page: 1)
                text = moviesData.description
            }
		}
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
