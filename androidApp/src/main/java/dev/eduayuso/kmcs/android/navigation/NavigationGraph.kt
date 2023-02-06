package dev.eduayuso.kmcs.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.eduayuso.kmcs.android.features.home.HomeView
import dev.eduayuso.kmcs.android.features.posts.detail.PostDetailView
import dev.eduayuso.kmcs.di.KoinViewModels
import dev.eduayuso.kmcs.features.posts.detail.PostDetailContract

@Composable
fun NavigationGraph(
    viewModels: KoinViewModels
) {

    val navController = rememberNavController()
    val startDestination = Routes.Home.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Routes.Home.route) {
            HomeView(navController, viewModels)
        }
        composable(
            route = "${Routes.PostDetail.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { navBackStackEntry ->
            PostDetailView(navController, viewModels.postDetail)
            val postId = navBackStackEntry.arguments?.getString("id")
            val event = PostDetailContract.Event.OnGetPostDetail(id = postId ?: "")
            viewModels.postDetail.setEvent(event)
        }
    }
}