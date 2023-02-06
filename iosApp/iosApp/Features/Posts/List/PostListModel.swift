import Shared

class PostListModel: ViewModelWrapper<PostListViewModel> {
    
    @Published var uiState: PostListContractState = .init(postList: [],
                                                        isLoading: false,
                                                        isError: false)
    
    init() {
        
        super.init(viewModel: KoinViewModels().postList)
        
        viewModel.collect(flow: viewModel.state, collect: { state in
            
            self.uiState = state as! PostListContractState
        })
        
        let event = PostListContractEventOnGetPostList()
        set(event: event)
    }
}
