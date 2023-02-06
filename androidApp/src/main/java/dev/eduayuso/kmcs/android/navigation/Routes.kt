package dev.eduayuso.kmcs.android.navigation

import dev.eduayuso.kmcs.AppConstants

sealed class Routes(val route: String) {

    object Home: Routes(AppConstants.RouteIds.home)
    object PostList: Routes(AppConstants.RouteIds.postList)
    object PostDetail: Routes(AppConstants.RouteIds.postDetail)
    object UserList: Routes(AppConstants.RouteIds.userList)
    object UserDetail: Routes(AppConstants.RouteIds.userDetail)
}
