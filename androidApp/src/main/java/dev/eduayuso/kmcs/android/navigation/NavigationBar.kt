package dev.eduayuso.kmcs.android.navigation

import dev.eduayuso.kmcs.android.R

data class NavigationItem(

    val name: String,
    val route: String,
    val image: Int,
)

object NavigationBar {

    val items = listOf(
        NavigationItem(
            name = "Feed",
            route = Routes.PostList.route,
            image = R.drawable.ic_baseline_image_24
        ),
        NavigationItem(
            name = "Friends",
            route = Routes.UserList.route,
            image = R.drawable.ic_baseline_group_24
        )
    )
}