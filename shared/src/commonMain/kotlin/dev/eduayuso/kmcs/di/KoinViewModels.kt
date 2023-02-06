package dev.eduayuso.kmcs.di

import dev.eduayuso.kmcs.features.posts.detail.PostDetailViewModel
import dev.eduayuso.kmcs.features.posts.list.PostListViewModel
import dev.eduayuso.kmcs.features.users.list.UserListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinViewModels: KoinComponent {

    val postList: PostListViewModel by inject()
    val userList: UserListViewModel by inject()
    val postDetail: PostDetailViewModel by inject()
}