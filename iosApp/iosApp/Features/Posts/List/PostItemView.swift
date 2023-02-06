import SwiftUI
import Shared

struct PostItemView: View {
    
    var post: PostEntity
    let picDimension: CGFloat = 48.0
    
    var body: some View {
        
        VStack {
            
            HStack {
                AsyncImage(url: URL(string: post.owner?.picture ?? "")) { image in
                    image
                        .resizable()
                        .cornerRadius(picDimension)
                        .frame(width: picDimension, height: picDimension, alignment: .center)
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
            }
            AsyncImage(url: URL(string: post.image ?? "")) { image in
                image
                    .resizable()
                    .cornerRadius(8)
                    .scaledToFit()
                    .frame(width: 256, height: 200, alignment: .center)
                    .clipped()
            } placeholder: {
                ProgressView()
                    .frame(width: 256, height: 256, alignment: .center)
            }
        }
    }
}

struct PostItemView_Previews: PreviewProvider {
    
    static var previews: some View {
        
        let owner: UserEntity = .init(id: "1",
                                      firstName: "aa",
                                      lastName: "bb",
                                      picture: "https://randomuser.me/api/portraits/med/men/10.jpg")
        
        let post: PostEntity = .init(id: "1",
                                     text: "Post example",
                                     image: "https://img.dummyapi.io/photo-1568480541687-16c2f73eea4c.jpg",
                                     likes: 3,
                                     publishDate: "01-02-2022",
                                     tags: [],
                                     owner: owner)
        
        PostItemView(post: post)
    }
}
