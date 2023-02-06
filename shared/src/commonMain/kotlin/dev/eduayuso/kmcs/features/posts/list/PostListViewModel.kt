package dev.eduayuso.kmcs.features.posts.list

import dev.eduayuso.kmcs.domain.interactors.GetPostListUseCase
import dev.eduayuso.kmcs.domain.interactors.type.Resource
import dev.eduayuso.kmcs.presentation.IViewModel
import dev.eduayuso.kmcs.presentation.ViewModel
import org.koin.core.component.inject

interface IPostListViewModel:
    IViewModel<PostListContract.Event, PostListContract.State, PostListContract.Effect>

open class PostListViewModel(

    private val getPostListUseCase: GetPostListUseCase

) : IPostListViewModel, ViewModel<PostListContract.Event, PostListContract.State, PostListContract.Effect>() {

    override fun createInitialState() = PostListContract.State()

    override fun handleEvent(event: PostListContract.Event) {

        when(event) {
            is PostListContract.Event.OnGetPostList -> getPostList()
            is PostListContract.Event.OnSelectPost -> selectPost(event.id)
        }
    }

    private fun getPostList() {

        setState {
            copy(isLoading = true)
        }
        collect(getPostListUseCase()) { result ->
            when (result) {
                is Resource.Error -> setState {
                    copy(isError = true, postList = emptyList())
                }
                is Resource.Success -> setState {
                    copy(isError = false, postList = result.data)
                }
            }
            setState {
                copy(isLoading = false)
            }
        }
    }

    private fun selectPost(id: String) {

        setEffect {
            PostListContract.Effect.NavigateToDetail(id)
        }
    }
}