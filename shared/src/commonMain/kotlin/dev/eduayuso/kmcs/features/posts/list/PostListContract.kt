package dev.eduayuso.kmcs.features.posts.list

import dev.eduayuso.kmcs.domain.entities.PostEntity
import dev.eduayuso.kmcs.domain.entities.UserEntity
import dev.eduayuso.kmcs.presentation.UIEffect
import dev.eduayuso.kmcs.presentation.UIEvent
import dev.eduayuso.kmcs.presentation.UIState

interface PostListContract {

    data class State(

        val postList: List<PostEntity>? = null,
        val isLoading: Boolean = false,
        val isError: Boolean = false

    ): UIState

    sealed interface Event: UIEvent {

        object OnGetPostList: Event
        data class OnSelectPost(val id: String): Event
    }

    sealed interface Effect: UIEffect {

        class NavigateToDetail(val id: String): Effect
    }
}