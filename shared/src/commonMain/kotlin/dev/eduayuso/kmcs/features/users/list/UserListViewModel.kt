package dev.eduayuso.kmcs.features.users.list

import dev.eduayuso.kmcs.domain.interactors.GetUserListUseCase
import dev.eduayuso.kmcs.domain.interactors.type.Resource
import dev.eduayuso.kmcs.presentation.IViewModel
import dev.eduayuso.kmcs.presentation.ViewModel

interface IUserListViewModel:
    IViewModel<UserListContract.Event, UserListContract.State, UserListContract.Effect>

open class UserListViewModel(

    private val getUserListUseCase: GetUserListUseCase

) : IUserListViewModel, ViewModel<UserListContract.Event, UserListContract.State, UserListContract.Effect>() {

    override fun createInitialState() = UserListContract.State()

    override fun handleEvent(event: UserListContract.Event) {

        when(event) {
            is UserListContract.Event.OnGetUserList -> getUserList()
            is UserListContract.Event.OnSelectUser -> selectUser(event.id)
        }
    }

    private fun getUserList() {

        setState {
            copy(isLoading = true)
        }
        collect(getUserListUseCase()) { result ->
            when (result) {
                is Resource.Error -> setState {
                    copy(isError = true, userList = emptyList())
                }
                is Resource.Success -> setState {
                    copy(isError = false, userList = result.data)
                }
            }
            setState {
                copy(isLoading = false)
            }
        }
    }

    private fun selectUser(id: String) {

        setEffect {
            UserListContract.Effect.NavigateToDetail(id)
        }
    }
}