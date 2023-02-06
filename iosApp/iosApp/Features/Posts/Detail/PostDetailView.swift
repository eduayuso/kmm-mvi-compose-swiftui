import SwiftUI
import Shared

struct PostDetailView: View {
    
    @ObservedObject var viewModel = PostDetailModel()
    
    let postId: String
    
    var body: some View {
        
        ZStack {
            
            if viewModel.uiState.isLoadingDetail {
                LoadingView()
            } else if let post = viewModel.uiState.post {
                VStack {
                    PostDetailContent(post: post)
                    if !viewModel.uiState.isLoadingComments {
                        CommentsView(comments: viewModel.uiState.comments ?? [])
                    }
                    Spacer()
                }
            } else {
                EmptyView(message: "Error fetching data")
            }
        }
        .navigationTitle(Text("\(viewModel.uiState.post?.text ?? "")"))
        .onAppear() {
            let event = PostDetailContractEventOnGetPostDetail(id: postId)
            viewModel.set(event: event)
        }
    }
}

struct PostDetailContent: View {
    
    let post: PostEntity
    let picDimension: CGFloat = 48
    
    var body: some View {
        
        VStack {
            
            HStack {
                AsyncImage(url: URL(string: post.owner?.picture ?? "")) { image in
                    image
                        .resizable()
                        .cornerRadius(picDimension)
                        .frame(width: picDimension, height: picDimension, alignment: .center)
                        .padding()
                } placeholder: {
                    ProgressView()
                        .frame(width: picDimension, height: picDimension, alignment: .center)
                }
                VStack(alignment: .leading) {
                    Text("\(post.owner?.firstName ?? "") \(post.owner?.lastName ?? "")")
                        .font(.title3)
                        .fontWeight(.semibold)
                        .foregroundColor(.gray)
                    Text(post.publishDate ?? "")
                        .font(.body)
                        .foregroundColor(.gray)
                }
                .padding()
                Spacer()
            }
            AsyncImage(url: URL(string: post.image ?? "")) { image in
                image
                    .resizable()
                    .cornerRadius(8)
                    .scaledToFit()
                    .frame(width: 348, height: 256, alignment: .center)
                    .clipped()
            } placeholder: {
                ProgressView()
                    .frame(width: 348, height: 256, alignment: .center)
            }
            Text(post.text ?? "")
                .font(.title2)
                .fontWeight(.light)
                .foregroundColor(.gray)
            .padding()
        }
    }
}

struct CommentsView: View {
    
    let comments: [CommentEntity]
    
    var body: some View {
     
        VStack {
            
            List(comments, id: \.self) { comment in
                CommentItemView(comment: comment)
            }
        }
    }
}

struct PostDetailView_Previews: PreviewProvider {
    
    static var previews: some View {
        
        PostDetailView(postId: "2")
    }
}
