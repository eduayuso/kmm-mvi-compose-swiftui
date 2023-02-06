import Shared

class UserListModel: ViewModelWrapper<UserListViewModel> {
    
    @Published var uiState: UserListContractState = .init(userList: nil,
                                                          isLoading: false,
                                                          isError: false)
    
    init() {
        
        super.init(viewModel: KoinViewModels().userList)
        
        viewModel.collect(flow: viewModel.state, collect: { state in
            
            self.uiState = state as! UserListContractState
        })
        
        let event = UserListContractEventOnGetUserList()
        set(event: event)
    }
}
