import SwiftUI
import Shared

struct PostListView: View {
    
    @ObservedObject var viewModel = PostListModel()
   
    var body: some View {
        
        NavigationView {
            
            VStack {
                
                List(viewModel.uiState.postList ?? [], id: \.self) { post in
                    NavigationLink {
                        PostDetailView(postId: post.id)
                    } label: {
                        PostItemView(post: post)
                    }
                    .isDetailLink(true)
                }
            }
        }
    }
}

struct PostListView_Previews: PreviewProvider {
    
    static var previews: some View {
    
        PostListView()
    }
}
