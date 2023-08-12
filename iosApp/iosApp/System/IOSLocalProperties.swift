import Foundation
import Shared

class IOSLocalProperties: LocalProperties {
    
    let dummyApiKey: String
    
    init() {
        
        self.dummyApiKey = Bundle.main.object(forInfoDictionaryKey: "DummyApiKey") as! String
    }
}
