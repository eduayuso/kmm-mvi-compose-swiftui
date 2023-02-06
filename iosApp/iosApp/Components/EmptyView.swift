import SwiftUI

struct EmptyView: View {
    
    private var message: String
    
    init(message: String) {
        
        self.message = message
    }
    
    var body: some View {
        
        VStack {
            Text(message)
          /*  Button(action: onClick) {
                Text("Check Again")
                    .font(.title3)
                    .foregroundColor(.white)
                    .padding()
                    .background(Color.blue)
                    .cornerRadius(10)
                    .shadow(radius: 10)
            }*/
        }
    }
}
