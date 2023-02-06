package dev.eduayuso.kmcs.features.posts.detail

import dev.eduayuso.kmcs.domain.interactors.GetPostCommentsUseCase
import dev.eduayuso.kmcs.domain.interactors.GetPostDetailUseCase
import dev.eduayuso.kmcs.domain.interactors.type.Resource
import dev.eduayuso.kmcs.presentation.IViewModel
import dev.eduayuso.kmcs.presentation.ViewModel

interface IPostDetailViewModel:
    IViewModel<PostDetailContract.Event, PostDetailContract.State, PostDetailContract.Effect>

open class PostDetailViewModel(

    private val getPostDetailUseCase: GetPostDetailUseCase,
    private val getPostCommentsUseCase: GetPostCommentsUseCase,

) : IPostDetailViewModel, ViewModel<PostDetailContract.Event, PostDetailContract.State, PostDetailContract.Effect>() {

    override fun createInitialState() = PostDetailContract.State()

    override fun handleEvent(event: PostDetailContract.Event) {

        when(event) {
            is PostDetailContract.Event.OnGetPostDetail -> getPostDetail(event.id)
            is PostDetailContract.Event.OnGetComments -> getPostComments(event.id)
        }
    }

    private fun getPostDetail(id: String) {

        setState {
            copy(isLoadingDetail = true)
        }
        collect(getPostDetailUseCase(id)) { result ->
            when (result) {
                is Resource.Error -> setState {
                    copy(isError = true, post = null)
                }
                is Resource.Success -> setState {
                    copy(isError = false, post = result.data)
                }
            }
            setState {
                copy(isLoadingDetail = false)
            }
            setEvent(PostDetailContract.Event.OnGetComments(id))
        }
    }

    private fun getPostComments(id: String) {

        setState {
            copy(isLoadingComments = true)
        }
        collect(getPostCommentsUseCase(id)) { result ->
            when (result) {
                is Resource.Error -> setState {
                    copy(isError = true, post = null)
                }
                is Resource.Success -> setState {
                    copy(isError = false, comments = result.data)
                }
            }
            setState {
                copy(isLoadingComments = false)
            }
        }
    }
}