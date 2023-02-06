import SwiftUI
import Shared

struct UserItemView: View {
    
    var user: UserEntity
    let picDimension: CGFloat = 48.0
    
    var body: some View {
        
        HStack {
            AsyncImage(url: URL(string: user.picture ?? "")) { image in
                image
                    .resizable()
                    .cornerRadius(picDimension)
                    .frame(width: picDimension, height: picDimension, alignment: .center)
            } placeholder: {
                ProgressView()
                    .frame(width: picDimension, height: picDimension, alignment: .center)
            }
            Text("\(user.firstName ?? "") \(user.lastName ?? "")")
                .font(.title3)
                .fontWeight(.semibold)
                .foregroundColor(.gray)
            .padding()
        }
    }
}

struct UserItemView_Previews: PreviewProvider {
    
    static var previews: some View {
        
        let user: UserEntity = .init(id: "1",
                                     firstName: "aa",
                                     lastName: "bb",
                                     picture: "https://randomuser.me/api/portraits/med/men/10.jpg")
        
        UserItemView(user: user)
    }
}
