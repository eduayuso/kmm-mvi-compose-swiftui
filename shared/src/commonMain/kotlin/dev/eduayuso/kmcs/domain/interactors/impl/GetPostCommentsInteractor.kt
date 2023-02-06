package dev.eduayuso.kmcs.domain.interactors.impl

import dev.eduayuso.kmcs.domain.entities.CommentEntity
import dev.eduayuso.kmcs.domain.interactors.GetPostCommentsUseCase
import dev.eduayuso.kmcs.domain.repository.IPostsRepository

class GetPostCommentsInteractor(

    private val repository: IPostsRepository

): GetPostCommentsUseCase() {

    override val block: suspend (param: String) -> List<CommentEntity>
        get() = {
            repository.getComments(it)
        }
}