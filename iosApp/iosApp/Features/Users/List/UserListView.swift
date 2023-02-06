import SwiftUI
import Shared

struct UserListView: View {
    
    @ObservedObject var viewModel = UserListModel()
   
    var body: some View {
        
        VStack {
                
            List(viewModel.uiState.userList ?? [], id: \.self) { user in
                UserItemView(user: user)
            }
        }
    }
}

struct UserListView_Previews: PreviewProvider {
    
    static var previews: some View {
    
        UserListView()
    }
}
