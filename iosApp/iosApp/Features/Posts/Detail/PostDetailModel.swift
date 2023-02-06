import Shared

class PostDetailModel: ViewModelWrapper<PostDetailViewModel> {
    
    @Published var uiState: PostDetailContractState =
        .init(post: nil,
              isLoadingDetail: false,
              isLoadingComments: false,
              comments: nil,
              isError: false)
    
    init() {
        
        super.init(viewModel: KoinViewModels().postDetail)
        
        viewModel.collect(flow: viewModel.state, collect: { state in
            
            self.uiState = state as! PostDetailContractState
        })
    }
}
