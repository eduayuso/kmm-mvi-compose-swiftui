import SwiftUI
import Shared

struct CommentItemView: View {
    
    var comment: CommentEntity
    let picDimension: CGFloat = 28.0
    
    var body: some View {
        
        VStack(alignment: .leading) {
            HStack {
                AsyncImage(url: URL(string: comment.owner?.picture ?? "")) { image in
                    image
                        .resizable()
                        .cornerRadius(picDimension)
                        .frame(width: picDimension, height: picDimension, alignment: .center)
                } placeholder: {
                    ProgressView()
                        .frame(width: picDimension, height: picDimension, alignment: .center)
                }
                Text("\(comment.owner?.firstName ?? "") \(comment.owner?.lastName ?? "")")
                    .font(.body)
                    .fontWeight(.semibold)
                    .foregroundColor(.gray)
                    .padding()
                Spacer()
            }
            Text(comment.message ?? "")
                .font(.body)
                .fontWeight(.light)
                .foregroundColor(.gray)
                .padding()
        }
    }
}
