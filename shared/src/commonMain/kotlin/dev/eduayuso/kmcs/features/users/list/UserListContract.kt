package dev.eduayuso.kmcs.features.users.list

import dev.eduayuso.kmcs.domain.entities.UserEntity
import dev.eduayuso.kmcs.presentation.UIEffect
import dev.eduayuso.kmcs.presentation.UIEvent
import dev.eduayuso.kmcs.presentation.UIState

interface UserListContract {

    data class State(

        val userList: List<UserEntity>? = null,
        val isLoading: Boolean = false,
        val isError: Boolean = false

    ): UIState

    sealed interface Event: UIEvent {

        object OnGetUserList: Event
        data class OnSelectUser(val id: String): Event
    }

    sealed interface Effect: UIEffect {

        class NavigateToDetail(val id: String): Effect
    }
}