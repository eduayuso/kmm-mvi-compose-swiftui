package dev.eduayuso.kmcs.features.posts.detail

import dev.eduayuso.kmcs.domain.entities.CommentEntity
import dev.eduayuso.kmcs.domain.entities.PostEntity
import dev.eduayuso.kmcs.presentation.UIEffect
import dev.eduayuso.kmcs.presentation.UIEvent
import dev.eduayuso.kmcs.presentation.UIState

interface PostDetailContract {

    data class State(

        val post: PostEntity? = null,
        val isLoadingDetail: Boolean = false,
        val isLoadingComments: Boolean = false,
        val comments: List<CommentEntity>? = null,
        val isError: Boolean = false

    ): UIState

    sealed interface Event: UIEvent {

        data class OnGetPostDetail(val id: String): Event
        data class OnGetComments(val id: String): Event
    }

    sealed interface Effect: UIEffect {

    }
}