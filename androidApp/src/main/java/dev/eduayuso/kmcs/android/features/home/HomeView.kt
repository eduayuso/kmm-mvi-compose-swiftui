package dev.eduayuso.kmcs.android.features.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.eduayuso.kmcs.android.features.posts.list.PostListView
import dev.eduayuso.kmcs.android.features.users.list.UserListView
import dev.eduayuso.kmcs.android.navigation.NavigationBar
import dev.eduayuso.kmcs.android.navigation.Routes
import dev.eduayuso.kmcs.di.KoinViewModels
import dev.eduayuso.kmcs.features.posts.list.PostListContract
import dev.eduayuso.kmcs.features.users.list.UserListContract

@Composable
fun HomeView(
    rootNavController: NavHostController,
    viewModels: KoinViewModels
) {

    val navController = rememberNavController()
    val startDestination = Routes.PostList.route

    val backStackEntry = navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBar.items.forEach { item ->

                    val selected = item.route == backStackEntry.value?.destination?.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = { navController.navigate(item.route) },
                        label = { Text(text = item.name) },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.image),
                                contentDescription = item.name
                            )
                        }
                    )
                }
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = startDestination,
            Modifier.padding(padding)
        ) {
            composable(Routes.PostList.route) {
                val viewModel = viewModels.postList
                PostListView(rootNavController, viewModel)
                viewModel.setEvent(PostListContract.Event.OnGetPostList)
            }
            composable(Routes.UserList.route) {
                val viewModel = viewModels.userList
                UserListView(rootNavController, viewModel)
                viewModel.setEvent(UserListContract.Event.OnGetUserList)
            }
        }
    }
}