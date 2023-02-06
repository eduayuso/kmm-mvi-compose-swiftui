import SwiftUI
import Shared

class ViewModelWrapper<ViewModelType: IViewModel>: ObservableObject {
    
    var viewModel: ViewModelType
    
    init(viewModel: ViewModelType) {
        
        self.viewModel = viewModel
    }
    
    func set(event: Any?) {
        
        viewModel.setEvent(event: event)
    }
}
