import SwiftUI


struct HomeView: View {
    
    var body: some View {
        
        TabView {
            PostListView()
                .tabItem {
                    Label("Feed", systemImage: "photo")
                }
            UserListView()
                .tabItem {
                    Label("Friends", systemImage: "person.2.fill")
                }
        }
    }
}
